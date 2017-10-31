package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class TokenFormatInValidException extends AuthorizationException {

    public TokenFormatInValidException() {
        super();
    }

    public TokenFormatInValidException(String message) {
        super(message);
    }

    public TokenFormatInValidException(Throwable cause) {
        super(cause);
    }

    public TokenFormatInValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
