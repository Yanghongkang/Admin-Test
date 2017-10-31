package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class AccountNotExistException extends AuthorizationException {

    public AccountNotExistException() {
        super();
    }

    public AccountNotExistException(String message) {
        super(message);
    }

    public AccountNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
