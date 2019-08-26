package com.yundotech.mymvp.ui.example;

public class DictDepartment {
    private Integer  departId;
    private String departName;

    public DictDepartment() {
        this.departId = null;
        this.departName = "";
    }

    public DictDepartment(Integer departId, String departName) {
        this.departId = departId;
        this.departName = departName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
