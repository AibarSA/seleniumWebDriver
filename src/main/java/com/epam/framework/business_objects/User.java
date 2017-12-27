package com.epam.framework.business_objects;

public enum User {
    USER1("automationTest@protonmail.com", "test123456");
    private String userName;
    private String password;

    User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
