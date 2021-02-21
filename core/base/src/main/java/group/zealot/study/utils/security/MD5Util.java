package group.zealot.study.utils.security;

import group.zealot.study.utils.Constants;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    /**
     * 加密
     */
    public static String encode(String plaintext) {
        return encode(plaintext.getBytes(Constants.DEFAULT_CHARSET));
    }

    /**
     * 加密
     */
    public static String encode(byte[] plaintext) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(plaintext);
            return Hex.encodeHexString(bytes, false);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
