package com.tuberculosis.entity.json;

/**
 * Created by Li Shaoqing
 * on 15/8/9.
 */
public class ValidatorError {

    private int errorCode;

    public ValidatorError(){}

    public ValidatorError(int errorCode){
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
