package com.kit.integrationmanager.notification;

import lombok.Data;


public enum NotificationType {
    REGISTRATION_SINGLE(1,"Single Registration"),
    REGISTRATION_BATCH (2, "Batch Registration"),
    LOGIN(3, "Login"),
    RESET_PASSWORD(4,"Reset Password");

    private int id;
    private String name;
    private NotificationType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
