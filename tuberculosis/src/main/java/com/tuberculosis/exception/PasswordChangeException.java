package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class PasswordChangeException extends AuthorizationException {

    public PasswordChangeException() {
        super();
    }

    public PasswordChangeException(String message) {
        super(message);
    }

    public PasswordChangeException(Throwable cause) {
        super(cause);
    }

    public PasswordChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
