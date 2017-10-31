package com.tuberculosis.auth;

import java.lang.annotation.*;

import org.springside.modules.persistence.Hibernates;

/**
 * @author Li ShaoQing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface   Authentication {
    String permission() default "user";
}
