package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class WrongPasswordException extends AuthorizationException {

    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException(Throwable cause) {
        super(cause);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
