package group.zealot.study.utils.security;

import group.zealot.study.utils.Constants;

import java.util.Base64;

public class Base64Util {
    /**
     * 编码（无换行符）
     */
    public static String encode(String plaintext) {
        return Base64.getEncoder().encodeToString(plaintext.getBytes(Constants.DEFAULT_CHARSET));
    }

    /**
     * 编码（无换行符）
     */
    public static byte[] encode(byte[] plaintext) {
        return Base64.getEncoder().encode(plaintext);
    }

    /**
     * URL编码（+ -> -   / -> _）
     */
    public static String urlEncode(String plaintext) {
        return Base64.getUrlEncoder().encodeToString(plaintext.getBytes(Constants.DEFAULT_CHARSET));
    }

    /**
     * 解码
     */
    public static String decode(String ciphertext) {
        return new String(decode(ciphertext.getBytes(Constants.DEFAULT_CHARSET)), Constants.DEFAULT_CHARSET);
    }

    /**
     * 解码
     */
    public static byte[] decode(byte[] ciphertext) {
        return Base64.getDecoder().decode(ciphertext);
    }

    /**
     * URL解码
     */
    public static byte[] urlDecode(byte[] ciphertext) {
        return Base64.getUrlDecoder().decode(ciphertext);
    }

}
