package com.yundotech.mymvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.yundotech.mymvp.network.IHttpClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import okhttp3.Request;

public class BaseModel<T> implements IBaseModel<T>{
    protected String TAG;
    private ArrayList<T>  mList;
    private Type mDataType;
    private Type mArrayType;
    private ArrayList<IBaseModelListener>  mListeners;
    private IHttpClient mHttpClient;
    private String mResourceApi;
    private Map<String, String> mFilters;
    private IHttpClient.IHttpRequestCallback mListCallback = new IHttpClient.IHttpRequestCallback() {
        @Override
        public void onSuccess(Request request, String result) {
            Gson gson = new Gson();
            Log.d(TAG, "onSuccess: "+result);
            T[] results = gson.fromJson(result, mArrayType);
            if (results != null) {
                mList = new ArrayList<>(Arrays.asList(results));
                notifyToListener(IBaseModelListener.ModelEventType.LIST, IBaseModelListener.OperateResultType.SUCCESS);
            }
            else {
                notifyToListener(IBaseModelListener.ModelEventType.LIST, IBaseModelListener.OperateResultType.NO_RESULT);
            }
        }

        @Override
        public void onFailure(Request request, Exception e) {
            notifyToListener(IBaseModelListener.ModelEventType.LIST, IBaseModelListener.OperateResultType.ERROR);
        }
    };
    private IHttpClient.IHttpRequestCallback mAddCallback = new IHttpClient.IHttpRequestCallback() {
        @Override
        public void onSuccess(Request request, String result) {
            notifyToListener(IBaseModelListener.ModelEventType.ADD, IBaseModelListener.OperateResultType.SUCCESS);
        }

        @Override
        public void onFailure(Request request, Exception e) {
            notifyToListener(IBaseModelListener.ModelEventType.ADD, IBaseModelListener.OperateResultType.ERROR);
        }
    };
    private IHttpClient.IHttpRequestCallback mUpdateCallback = new IHttpClient.IHttpRequestCallback() {
        @Override
        public void onSuccess(Request request, String result) {
            notifyToListener(IBaseModelListener.ModelEventType.MODIFY, IBaseModelListener.OperateResultType.SUCCESS);
        }

        @Override
        public void onFailure(Request request, Exception e) {
            notifyToListener(IBaseModelListener.ModelEventType.MODIFY, IBaseModelListener.OperateResultType.ERROR);
        }
    };
    private IHttpClient.IHttpRequestCallback mDeleteCallback = new IHttpClient.IHttpRequestCallback() {
        @Override
        public void onSuccess(Request request, String result) {
            notifyToListener(IBaseModelListener.ModelEventType.DELETE, IBaseModelListener.OperateResultType.SUCCESS);
        }

        @Override
        public void onFailure(Request request, Exception e) {
            notifyToListener(IBaseModelListener.ModelEventType.DELETE, IBaseModelListener.OperateResultType.ERROR);
        }
    };

    // mResourceApi: must be RESTful API, support GET, POST, PUT, DELETE
    public BaseModel(IHttpClient mHttpClient, String mResourceApi, Type dataType, Type arrayType) {
        this.TAG = this.getClass().getName();
        this.mListeners = new ArrayList<>();
        this.mHttpClient = mHttpClient;
        this.mResourceApi = mResourceApi;
        this.mList = new ArrayList<>();
        this.mDataType = dataType;
        this.mArrayType = arrayType;
        this.mFilters = null;
    }

    @Override
    public void addListener(IBaseModelListener listener) {
        mListeners.add(listener);
    }

    @Override
    public void removeListener(IBaseModelListener listener) {
        mListeners.remove(listener);
    }

    @Override
    public boolean fetchList() {
        return mHttpClient.asyncGet(mResourceApi, this.mFilters, mListCallback);
    }

    @Override
    public boolean addNewOne(Object one) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(one);
        return mHttpClient.asyncPost(mResourceApi, jsonBody, mAddCallback);
    }

    @Override
    public boolean modifyOne(Object one) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(one);
        return mHttpClient.asyncPut(mResourceApi, jsonBody, mUpdateCallback);
    }

    @Override
    public boolean deleteOne(Object one) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(one);
        return mHttpClient.asyncDelete(mResourceApi, jsonBody, mDeleteCallback);
    }

    @Override
    public boolean setFilters(Map<String, String> filters) {
        this.mFilters = filters;
        this.fetchList();
        return true;
    }

    @Override
    public boolean clearFilters() {
        this.mFilters = null;
        this.fetchList();
        return true;
    }

    @Override
    public ArrayList<T> getList() {
        return mList;
    }

    @Override
    public Type getDataType() {
        return this.mDataType;
    }

    private void notifyToListener(IBaseModelListener.ModelEventType event, IBaseModelListener.OperateResultType type) {
        for (IBaseModelListener listener : mListeners) {
            listener.onOperateResult(this, event, type);
        }
    }
}
