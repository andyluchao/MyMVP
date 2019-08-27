package com.yundotech.mymvp.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface definition template for Model
 * @param <T> the data's class for model, which has all the fields of one certain database's table
 */
public interface IBaseModel<T> {
    /**
     * the presenter should implement IBaseModelListener and call this function of each model.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @param listener the operation listener of this model
     */
    void addListener(IBaseModelListener listener);

    /**
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @param listener the operation listener of this model
     */
    void removeListener(IBaseModelListener listener);

    /**
     * to fetch a list of all data from server.<br/>
     * Note: sub-class of BasePresenter will call it while updating list.
     * @return true for fetching request is sent successfully, or else false
     */
    boolean fetchList();

    /**
     * to save a new item to server.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @param one the data to add whose class type must be the model's T type
     * @return true for adding request is sent successfully, or else false
     */
    boolean addNewOne(Object one);

    /**
     * to modify a certain item in server.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @param one the data to modify whose class type must be the model's T type
     * @return true for modifying request is sent successfully, or else false
     */
    boolean modifyOne(Object one);

    /**
     * to delete a certain item in server.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @param one the data to add whose class type must be the model's T type
     * @return true for deleting request is sent successfully, or else false
     */
    boolean deleteOne(Object one);

    /**
     * to fetch data list by some filters.
     * @param filters a map to store filters, which will be add to the URL as parameters
     * @return true for fetching request with filters is sent successfully, or else false
     */
    boolean setFilters(Map<String, String> filters);

    /**
     * to clear filters and fetch a list of all data
     * @return true for fetching request without filters is sent successfully, or else false
     */
    boolean clearFilters();

    /**
     * to get current data list.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @return an array list of all data
     */
    ArrayList<T> getList();

    /**
     * to get data's class type in this model.<br/>
     * Note: It's called in BasePresenter, and no need to call it in sub-class of BasePresenter.
     * @return the class type of this model's data
     */
    Type getDataType();
}
