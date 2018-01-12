package com.epam.framework.business_objects;

public enum User {
    MAIN_USER("arraylist@protonmail.com", "test123456");
    private final String userName;
    private final String password;

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
