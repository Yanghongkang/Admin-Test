package com.tuberculosis.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {

    private static Logger logger = LoggerFactory.getLogger(HMAC.class);

	public static final String HMAC_MD5 = "HmacMD5";
	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final String HMAC_SHA256 = "HmacSHA256";

	private Mac mac;

	public HMAC(String secretKey) {
		this(secretKey, HMAC_SHA1);
	}
	
	public HMAC(String secretKey, String algorithm) {
		if (!HMAC_SHA1.equals(algorithm) && !HMAC_MD5.equals(algorithm) && !HMAC_SHA256.equals(algorithm)) {
			throw new IllegalArgumentException("Not supported algorithm:" + algorithm);
		}
		
		SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(), algorithm);
		try {
			this.mac = Mac.getInstance(algorithm);
			this.mac.init(secret);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			logger.error("Extremely severe problem, cannot create HMAC", e);
		}
    }
	
	public String toBase16(String input) {
		byte[] result = this.mac.doFinal(input.getBytes());
		return HexUtil.toHex(result);
	}

    public String toBase64(String input) {
        byte[] result = this.mac.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    public String toUrlBase64(String input) {
        byte[] result = this.mac.doFinal(input.getBytes());
        return Base64.getUrlEncoder().encodeToString(result);
    }

}
