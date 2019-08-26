package com.yundotech.mymvp.ui.example;

import com.yundotech.mymvp.model.BaseModel;
import com.yundotech.mymvp.network.IHttpClient;

public class DictDepartmentModel extends BaseModel<DictDepartment> {
    public DictDepartmentModel(IHttpClient httpClient) {
        super(httpClient,"/dicDepart/dicDepartList", DictDepartment.class, DictDepartment[].class);
    }
}
