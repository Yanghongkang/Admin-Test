package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class TokenEmptyException extends AuthorizationException {

    public TokenEmptyException() {
        super();
    }

    public TokenEmptyException(String message) {
        super(message);
    }

    public TokenEmptyException(Throwable cause) {
        super(cause);
    }

    public TokenEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
