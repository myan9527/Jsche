package org.jsche.common.exception;

import org.jsche.common.ErrorMessage;

public class ValidateException extends RuntimeException {
    private static final long serialVersionUID = 549894876259051580L;

    public ValidateException(ErrorMessage message) {
        super(message.getErrorMessage());
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Throwable th, ErrorMessage message) {
        super(message.getErrorMessage(), th);
    }
}
