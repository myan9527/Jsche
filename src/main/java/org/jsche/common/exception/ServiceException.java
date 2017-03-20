package org.jsche.common.exception;

public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = -4906641650749448734L;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable th, String message){
        super(message, th);
    }
}
