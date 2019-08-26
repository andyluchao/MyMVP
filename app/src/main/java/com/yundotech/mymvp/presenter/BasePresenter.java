package com.yundotech.mymvp.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.yundotech.mymvp.contract.IBaseContract;
import com.yundotech.mymvp.model.IBaseModel;
import com.yundotech.mymvp.model.IBaseModelListener;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

abstract public class BasePresenter<IView extends IBaseContract.IBaseView>
        extends BroadcastReceiver
        implements IBaseContract.IBasePresenter<IView>, IBaseModelListener {

    private String INTENT_MODEL_NOTIFY;
    private String INTENT_MODEL_CLASS;
    private String INTENT_EVENT_TYPE;
    private String INTENT_OP_RESULT;
    protected String TAG;
    protected IView mMyView;
    private Context mContext;
    private IBaseContract.BaseState mState;
    private LocalBroadcastManager mLBM;
    private Map<Class, IBaseModel>  mModelMap;

    protected BasePresenter() {
        this.TAG = this.getClass().getName();
        this.INTENT_MODEL_NOTIFY = this.TAG + ".BaseModel";
        this.INTENT_MODEL_CLASS = this.TAG + ".ModelClass";
        this.INTENT_EVENT_TYPE = this.TAG + ".EventType";
        this.INTENT_OP_RESULT = this.TAG + ".Result";
        this.mMyView = null;
        this.mContext = null;
        this.mState = IBaseContract.BaseState.NONE;
        this.mModelMap = new HashMap<>();
    }

    protected Class addModel(IBaseModel model) {
        Class key = model.getClass();
        mModelMap.put(key, model);
        return key;
    }

    protected void removeModel(Class type) {
        mModelMap.remove(type);
    }

    protected IBaseModel getModel(Class classType) {
        return mModelMap.get(classType);
    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        this.mLBM = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_MODEL_NOTIFY);
        this.mLBM.registerReceiver(this, intentFilter);
    }

    @Override
    public void onCreate(Bundle savedInfo) {
        this.mState = IBaseContract.BaseState.INITIALIZED;
    }

    @Override
    public void onStart(IView view) {
        this.mMyView = view;
        this.mState = IBaseContract.BaseState.SHOWING;
        for (IBaseModel model: mModelMap.values()) {
            model.addListener(this);
            model.fetchList();
        }
    }

    @Override
    public void onResume() {
        this.mState = IBaseContract.BaseState.RUNNING;
    }

    @Override
    public void onPause() {
        this.mState = IBaseContract.BaseState.SHOWING;
    }

    @Override
    public void onStop() {
        for (IBaseModel model: mModelMap.values()) {
            model.removeListener(this);
        }
        this.mState = IBaseContract.BaseState.INITIALIZED;
        this.mMyView = null;
    }

    @Override
    public void onSaveState(Bundle savedInfo) {

    }

    @Override
    public void onDestroy() {
        this.mState = IBaseContract.BaseState.NONE;
    }

    @Override
    public void onDetach() {
        this.mLBM.unregisterReceiver(this);
        this.mLBM = null;
        this.mModelMap.clear();
        this.mContext = null;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Class modelClass = (Class)intent.getSerializableExtra(INTENT_MODEL_CLASS);
        IBaseModel model = this.getModel(modelClass);
        ModelEventType eventType = (ModelEventType) intent.getSerializableExtra(INTENT_EVENT_TYPE);
        OperateResultType result = (OperateResultType) intent.getSerializableExtra(INTENT_OP_RESULT);
        Log.d(TAG, "onReceive: intent is " + action + ", model: " + modelClass + ", event: "+eventType);
        this.viewJobWith(model, eventType, result);
    }

    @Override
    public void onOperateResult(IBaseModel src, ModelEventType event, OperateResultType type) {
        LocalBroadcastManager lbm = this.mLBM;
        // save broadcast manager instance in local area, as this.mLBM maybe is set to null in onDetach()
        // TODO: Is it safe to call sendBroadcast() while the context of broadcast manager is destroyed?
        if (lbm != null) {
            Intent intent = new Intent(INTENT_MODEL_NOTIFY);
            intent.putExtra(INTENT_MODEL_CLASS, src.getClass());
            intent.putExtra(INTENT_EVENT_TYPE, event);
            intent.putExtra(INTENT_OP_RESULT, type);
            lbm.sendBroadcast(intent);
        }
    }

    @Override
    public boolean createData(Object one) {
        Type dataType = one.getClass();
        for (IBaseModel model : this.mModelMap.values()) {
            if (dataType == model.getDataType()) {
                return model.addNewOne(one);
            }
        }
        return false;
    }

    @Override
    public boolean modifyData(Object one) {
        Type dataType = one.getClass();
        for (IBaseModel model : this.mModelMap.values()) {
            if (dataType == model.getDataType()) {
                return model.modifyOne(one);
            }
        }
        return false;
    }

    @Override
    public boolean deleteData(Object one) {
        Type dataType = one.getClass();
        for (IBaseModel model : this.mModelMap.values()) {
            if (dataType == model.getDataType()) {
                return model.deleteOne(one);
            }
        }
        return false;
    }

    // implement this function in sub-class and do UI updating or showing notification
    protected abstract void viewJobWith(IBaseModel model, ModelEventType eventType, OperateResultType result);
}
