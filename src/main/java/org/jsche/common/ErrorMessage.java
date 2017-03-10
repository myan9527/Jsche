package org.jsche.common;

public enum ErrorMessage {
    EMAIL_REGISTERED("This email has been used, please change another one.");
    
    
    private String errorMessage;
    ErrorMessage(String msg){
        this.errorMessage = msg;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
