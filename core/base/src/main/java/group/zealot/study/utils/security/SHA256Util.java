package group.zealot.study.utils.security;

import group.zealot.study.utils.Constants;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class SHA256Util {
    public static String encode(String plaintext) {
        return encode(plaintext.getBytes(Constants.DEFAULT_CHARSET));
    }

    public static String encode(byte[] plaintext) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plaintext);
            return Hex.encodeHexString(messageDigest.digest(), false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
