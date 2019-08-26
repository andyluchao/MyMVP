package com.yundotech.mymvp.ui.example;

import com.yundotech.mymvp.contract.IBaseContract;

import java.util.ArrayList;

public interface IUserMgrPresenter<IView extends IBaseContract.IBaseView> extends IBaseContract.IBasePresenter<IView> {
    ArrayList<UserInfo> getUserList();
    String getDepartment(int id);
}
