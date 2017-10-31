package com.tuberculosis.auth;

import com.google.common.base.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Li ShaoQing
 */
public class AuthTokenGenerate {

    private static final Pattern TOKEN_PATTERN_SHA1 = Pattern.compile(
            "(\\d+)\\|(\\d+)\\|(\\w{40})");

    private static final String KEY = "AS*DF!@#$%^as)_df3f4";

    /**
     * generate HAMC-MD5 token in the format
     * <code>
     * &lt;uuid&gt;|&lt;create time&gt;|&lt;hmac-md5 of &lt;create&gt;&lt;uuid&gt;&lt;secretkey&gt;
     * </code>
     */
    public static String generateToken(String uid) {
        String info = uid + "|" + System.nanoTime();
        return info + "|" + new HMAC(KEY).toBase16(info);
    }

    public static String generateToken(String uid, long createTime) {
        String info = uid + "|" + createTime;
        return info + "|" + new HMAC(KEY).toBase16(info);
    }

    public static boolean isTokenFormatValid(String token) {
        Matcher matcher = TOKEN_PATTERN_SHA1.matcher(token);
        if (!matcher.matches()) {
            return false;
        }
        String uuidExtracted = matcher.group(1);
        String createTime = matcher.group(2);
        String generatedToken = generateToken(uuidExtracted, Long.valueOf(createTime));
        return Objects.equal(generatedToken, token);
    }
}
