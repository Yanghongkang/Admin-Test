package com.tuberculosis.exception;

/**
 * @author Li ShaoQing
 */
public class SettingNotFoundException extends ServiceException {

    public SettingNotFoundException() {
        super();
    }

    public SettingNotFoundException(String message) {
        super(message);
    }

    public SettingNotFoundException(Throwable cause) {
        super(cause);
    }

    public SettingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
