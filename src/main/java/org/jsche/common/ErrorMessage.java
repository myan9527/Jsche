package org.jsche.common;

public enum ErrorMessage {
    EMAIL_REQUIRED("Please supply your email."),
    EMAIL_REGISTERED("This email has been used, please change another one."),
    UNMATCHED_PASSWORD("Please confirm entered password."),
    PASSWORD_REQUIRED("Please enter your password."),
    NO_SUCH_USER("User not exist."),
    INVALID_PASSWORD("Incorrect password."),
    LOGIN_REQUIRED("Please login first.");

    private final String errorMessage;

    ErrorMessage(String msg) {
        this.errorMessage = msg;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
