package org.jsche.common.exception;

import org.jsche.common.ErrorMessage;

public class ValidationException extends RuntimeException{
    private static final long serialVersionUID = 549894876259051580L;
    public ValidationException(ErrorMessage message){
        super(message.getErrorMessage());
    }
    
    public ValidationException(String message){
        super(message);
    }

    public ValidationException(Throwable th, ErrorMessage message) {
        super(message.getErrorMessage(), th);
    }
}
