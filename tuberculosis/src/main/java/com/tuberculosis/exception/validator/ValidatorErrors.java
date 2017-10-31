package com.tuberculosis.exception.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li ShaoQing
 */
public class ValidatorErrors {

    private Map<String,String> errors = new HashMap<>();

    public void addError(String field, String message){
        errors.put(field, message);
    }

    public void removeError(String field) {
        errors.remove(field);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String,String> getAllErrors(){
        return errors;
    }
}
