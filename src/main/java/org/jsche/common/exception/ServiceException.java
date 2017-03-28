package org.jsche.common.exception;

import org.jsche.common.ErrorMessage;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4906641650749448734L;

    public ServiceException(ErrorMessage message) {
        super(message.getErrorMessage());
    }

    public ServiceException(Throwable th, ErrorMessage message) {
        super(message.getErrorMessage(), th);
    }
}
