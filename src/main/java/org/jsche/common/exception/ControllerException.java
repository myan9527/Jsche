package org.jsche.common.exception;

public class ControllerException extends RuntimeException{

    private static final long serialVersionUID = 4575067931762249396L;
    
    public ControllerException(String message){
        super(message);
    }

    public ControllerException(Throwable th, String message){
        super(message, th);
    }
}
