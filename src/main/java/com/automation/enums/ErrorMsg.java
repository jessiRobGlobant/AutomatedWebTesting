package com.automation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Test users for the app.
 */
@Getter
@AllArgsConstructor
public enum ErrorMsg {
    DATA_MATCH("Epic sadface: Username and password do not match any user in this service"),
    REQUIRED_USERNAME("Epic sadface: Username is required"),
    REQUIRED_PASSWORD("Epic sadface: Password is required");

    private final String msg;
}
