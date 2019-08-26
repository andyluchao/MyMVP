package com.yundotech.mymvp.ui.example;

public class UserInfo {
    static final String defaultPassword = "123456";
    static final int defaultRoleId = 1; // common
    static final int defaultDepart = 1; // all company
    private String userId;
    private int roleId;
    private String name;
    private int departId;
    private String phone;
    private String passWord;

    public UserInfo() {
        this.userId = null;
        this.name = null;
        this.roleId = defaultRoleId;
        this.departId = defaultDepart;
        this.phone = null;
        this.passWord = defaultPassword;
    }

    public UserInfo(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.roleId = defaultRoleId;
        this.departId = defaultDepart;
        this.phone = null;
        this.passWord = defaultPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
