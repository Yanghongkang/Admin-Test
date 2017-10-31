package com.tuberculosis.util;

import com.tuberculosis.exception.validator.ValidatorErrors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Li ShaoQing
 */
public class ValidatorUtil {

    // ---------------  field ---------------------------
    public static final String FIELD_ACCOUNT = "account";
    public static final String FIELD_MOBILE = "mobile";
    public static final String FIELD_PASSWORD = "password";  // only for controller use
    public static final String FIELD_PLAIN_PASSWORD = "plainPassword";
    public static final String FIELD_CONFIRM_PASSWORD = "confirmPassword";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_REAL_NAME = "realName";
    public static final String FIELD_IDENTITY_ID = "identityId";
    public static final String FIELD_CONFIRM_IDENTITY_ID = "confirmIdentityId";
    public static final String FIELD_QQ = "qq";

    // ---------------  message -------------------------
    public static final String MSG_IS_BLANK = "不能为空";
    public static final String MSG_ACCOUNT = "5-20个小写英文字母或数字组成";
    public static final String MSG_MOBILE = "手机号码格式错误";
    public static final String MSG_PASSWORD = "6-20个字符，不含空格";
    public static final String MSG_CONFIRM_PASSWORD = "两次输入密码不一致";
    public static final String MSG_EMAIL = "邮箱格式错误";
    public static final String MSG_REAL_NAME = "真实姓名";
    public static final String MSG_IDENTITY_ID = "身份证号码格式错误";
    public static final String MSG_CONFIRM_IDENTITY_ID = "两次输入身份证号码不一致";
    public static final String MSG_QQ = "QQ号码格式错误";

    // ---------------  regex ---------------------------
    public static final String REGEX_ACCOUNT = "^[0-9a-z]{5,20}$";
    public static final String REGEX_MOBILE = "^0?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
    public static final String REGEX_PASSWORD = "^[^\\s]{6,20}$";
    public static final String REGEX_EMAIL = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
    public static final String REGEX_REAL_NAME = "^[\\u4e00-\\u9fa5]{2,4}$";
    public static final String REGEX_IDENTITY_ID = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)|\\d{15}$";
    public static final String REGEX_QQ = "^[1-9][0-9]{4,}$";


    public static void validatorPassword(String newPassword, String confirmPassword, ValidatorErrors errors) {
        if(StringUtils.isBlank(newPassword)) {
            errors.addError(FIELD_PLAIN_PASSWORD, MSG_IS_BLANK);
        }

        if(StringUtils.isBlank(confirmPassword)){
            errors.addError(FIELD_CONFIRM_PASSWORD, MSG_IS_BLANK);
        } else {
            if(!newPassword.equals(confirmPassword)) errors.addError(FIELD_CONFIRM_PASSWORD, MSG_CONFIRM_PASSWORD);
        }
    }

}
