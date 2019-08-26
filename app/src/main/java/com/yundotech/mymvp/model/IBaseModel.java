package com.yundotech.mymvp.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public interface IBaseModel<T> {
    // 1. presenter's callback interface
    void addListener(IBaseModelListener listener);
    void removeListener(IBaseModelListener listener);

    // 2. list
    boolean fetchList();
    // 3. add
    boolean addNewOne(Object one);
    // 4. modify
    boolean modifyOne(Object one);
    // 5. delete
    boolean deleteOne(Object one);
    // 6. set filter
    boolean setFilters(Map<String, String> filters);
    boolean clearFilters();

    // 7. get result from list or search
    ArrayList<T> getList();

    Type getDataType();
}
