package com.yundotech.mymvp.ui.example;

import com.yundotech.mymvp.data.ComUIType;
import com.yundotech.mymvp.data.ComWarningType;
import com.yundotech.mymvp.model.IBaseModel;
import com.yundotech.mymvp.network.HttpClient;
import com.yundotech.mymvp.network.IHttpClient;
import com.yundotech.mymvp.presenter.BasePresenter;

import java.util.ArrayList;

public class UserMgrPresenter extends BasePresenter<IUserMgrView<ComUIType, ComWarningType>> implements IUserMgrPresenter<IUserMgrView<ComUIType, ComWarningType>> {
    private ArrayList<UserInfo> mUserList;
    private ArrayList<DictDepartment> mDepartments;
    private Class userInfoModelKey;
    private Class departmentModelKey;

    UserMgrPresenter() {
        IHttpClient httpClient = new HttpClient("http://192.168.10.144:8080", "token");
        this.userInfoModelKey = this.addModel(new UserInfoModel(httpClient));
        this.departmentModelKey = this.addModel(new DictDepartmentModel(httpClient));
        mUserList = new ArrayList<>();
        mDepartments = new ArrayList<>();
    }

    @Override
    public ArrayList<UserInfo> getUserList() {
        return mUserList;
    }

    @Override
    public String getDepartment(int id) {
        for (DictDepartment department:mDepartments) {
            if (department.getDepartId().equals(id)) {
                return department.getDepartName();
            }
        }
        return "";
    }

    public void viewJobWith(IBaseModel model, ModelEventType eventType, OperateResultType result) {
        if (mMyView != null && result != OperateResultType.SUCCESS) {
            if (result == OperateResultType.NO_RESULT) {
                mMyView.notifyWarning(ComWarningType.NO_RESULT);
            }
            else if (result == OperateResultType.ERROR) {
                mMyView.notifyWarning(ComWarningType.NETWORK_ERROR);
            }
        }
        else if (model instanceof UserInfoModel) {
            UserInfoModel userInfoModel = (UserInfoModel)model;
            mUserList = userInfoModel.getList();
            if (mMyView != null && eventType == ModelEventType.LIST) {
                mMyView.notifyUpdateUI(ComUIType.UNIQUE_LIST);
            }
        }
        else if (model instanceof DictDepartmentModel) {
            DictDepartmentModel ddModel = (DictDepartmentModel)model;
            mDepartments = ddModel.getList();
            if (mMyView != null && eventType == ModelEventType.LIST) {
                mMyView.notifyUpdateUI(ComUIType.UNIQUE_LIST);
            }
        }
    }
}
