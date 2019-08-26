package com.yundotech.mymvp.model;

public interface IBaseModelListener {
    enum ModelEventType {
        ADD,
        DELETE,
        MODIFY,
        LIST
    }
    enum OperateResultType {
        SUCCESS,
        ERROR,
        NO_RESULT,
        ALREADY_EXIST
    }
    void onOperateResult(IBaseModel src, ModelEventType event, OperateResultType type);
}
