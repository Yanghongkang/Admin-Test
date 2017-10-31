package com.tuberculosis.constant;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Li ShaoQing
 */
public class CommonContants {

    public static final String FIELD_ID = "id";

    public static Map<Integer, String> allStatus = Maps.newHashMap();

    static {
        allStatus.put(0, "有效");
        allStatus.put(1, "无效");
    }

    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public static final int TOKEN_EXPIRE_DATE = 8;

    public static final long SETTING_ID = 1;

    public static final int IS_FIRST_LOGIN = 0;
    public static final int NOT_FIRST_LOGIN = 1;

    /* -----  结核诊断结果（TB_DIAGNOSIS） ------
   -1. 未填写
   0. 非结核；1. 普通结核初治；2. 普通结核复治；3. 单耐药结核；4. 多耐药结核
   5.耐多药结核(MDR-TB)； 6. 广泛耐药结核（XDR-TB）
     */
    public static final int DIAGNOSIS_TYPE_NULL = -1;
    public static final int DIAGNOSIS_TYPE_FEI = 0;
    public static final int DIAGNOSIS_TYPE_PUTONG_CHU = 1;
    public static final int DIAGNOSIS_TYPE_PUTONG_FU = 2;
    public static final int DIAGNOSIS_TYPE_DAN_NY = 3;
    public static final int DIAGNOSIS_TYPE_DUO_NY = 4;
    public static final int DIAGNOSIS_TYPE_NAIDUOYAO = 5;
    public static final int DIAGNOSIS_TYPE_GUANGFAN_NY = 6;

    public static final String MX_NOT_TREAT = "未治疗";
    public static final String MX_NOT_STOP = "终止";
    public static final int THEARAPY_STATUS_NOT = 0;
    public static final int THEARAPY_STATUS_CREATE = 1;
    public static final int THEARAPY_STATUS_FOLLOW = 2;
    public static final int THEARAPY_STATUS_STOP = 3;
    public static final int THEARAPY_STATUS_STOP_BAD = 4;
    public static final int THEARAPY_STATUS_STOP_Voluntary = 5;
    public static final int THEARAPY_STATUS_STOP_EXCLUDE = 6;
    public static final int THEARAPY_STATUS_STOP_OUT = 7;
//    public static final Long BEFORE_TREAT = -1L; // 治疗前
//    public static final String BEFORE_TREAT_MX = "治疗前"; // 治疗前
//    public static final int BEFORE_TREAT_tbDiagnosis = -1; // 治疗前

    // ---------------------
//    public static final int MX_DATA_TYPE_PRE = 1;
//    public static final int MX_DATA_TYPE_SAVE = 2;

    public static final int MX_DATA_STATUS_COMMON = 1;
    public static final int MX_DATA_STATUS_DEL = 2;
    
    public static final int 计量提醒 = 1;
    public static final int 计量不提醒 = 2;


    public static final int MX_DATA_NO_THERAPY = -1;
    public static final int MX_DATA_NO_TbDiagnosis = -1;

    public static final int IMPORTANCE_MONTH = 1;
    public static final int DATA_INCOMPLETE = 1;

    public static final int RETURN_STATUS_ALL = 1;
    public static final int RETURN_STATUS_NOT_RETURN = 2;
    public static final int RETURN_STATUS_RETURN = 3;
    public static final int RETURN_STATUS_NOT_COMPLY = 4;

    // 随访 未随访 时间未到
    public static final int DATA_STATUS_FUTURE = 0;
    public static final int DATA_STATUS_DO = 1;
    public static final int DATA_STATUS_NOT_DO = 2;
    public static final int DATA_STATUS_NOT_NEED = 3;
    //------------- error code -----------------------------
    public static final int ERROR_CODE_TOKEN = 100001;
    public static final int ERROR_CODE_PWD_WRONG = 100002;
    public static final int ERROR_CODE_LOGOUT_ERROR = 100003;
    public static final int ERROR_CODE_CHANGE_PWD = 100004;
    public static final int ERROR_CODE_ADMIN_INFO = 100005;
    public static final int ERROR_CODE_PT_INFO_NOT_EXIST = 100006;
    public static final int ERROR_CODE_PT_INFO_ID_CODE_EXIST = 100007;
    public static final int ERROR_CODE_PT_INFO_HOSPITAL_NO_EXIST = 100008;
}
