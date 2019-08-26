package com.yundotech.mymvp.ui.example;

import com.yundotech.mymvp.model.BaseModel;
import com.yundotech.mymvp.network.IHttpClient;

public class UserInfoModel extends BaseModel<UserInfo> {
    public UserInfoModel(IHttpClient mHttpClient) {
        super(mHttpClient,"/infoUser/infoUserList", UserInfo.class, UserInfo[].class);
    }
}
