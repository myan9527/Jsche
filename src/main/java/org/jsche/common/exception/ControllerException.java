package org.jsche.common.exception;

import org.jsche.common.ErrorMessage;

public class ControllerException extends RuntimeException {

    private static final long serialVersionUID = 4575067931762249396L;

    public ControllerException(ErrorMessage message) {
        super(message.getErrorMessage());
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable th, ErrorMessage message) {
        super(message.getErrorMessage(), th);
    }
}
