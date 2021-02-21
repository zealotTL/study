package group.zealot.study.utils.security;

import group.zealot.study.utils.Constants;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

/**
 * AES128 算法
 * <p>
 * CBC 模式
 * <p>
 * PKCS7Padding 填充模式
 * <p>
 * CBC模式需要添加一个参数iv
 * <p>
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
 * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 *
 * @author zealotTL
 * @date 2020-05-15 17:37
 */
public class AESUtil {
    private static Logger logger = LoggerFactory.getLogger(AESUtil.class);
    // 算法名称
    private static final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    private static final String AES_CBC_PKCS_7_PADDING = "AES/CBC/PKCS7Padding";
    private static final String BC = "BC";

    private static final int BIT = 16;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static Key init(byte[] key) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        if (key.length % BIT != 0) {
            int groups = key.length / BIT + (key.length % BIT != 0 ? 1 : 0);
            byte[] temp = new byte[groups * BIT];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }
        // 转化成JAVA的密钥格式
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static String encode(String plaintext, String key, String iv) {
        return new String(encode(plaintext.getBytes(Constants.DEFAULT_CHARSET),
                key.getBytes(Constants.DEFAULT_CHARSET),
                iv.getBytes(Constants.DEFAULT_CHARSET)));
    }

    /**
     * 加密方法
     *
     * @param plaintext 要加密的字符串
     * @param key       加密密钥
     * @param iv        加密向量
     */
    public static byte[] encode(byte[] plaintext, byte[] key, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_7_PADDING, BC);
            cipher.init(Cipher.ENCRYPT_MODE, init(key), new IvParameterSpec(iv));
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            logger.error("plaintext {}", plaintext);
            logger.error("key {}", key);
            logger.error("iv {}", iv);
            logger.error("encrypt 异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密方法
     *
     * @param ciphertext 要解密的字符串
     * @param key        解密密钥
     * @param iv         加密向量
     */
    public static String decode(String ciphertext, String key, String iv) {
        return new String(decode(ciphertext.getBytes(Constants.DEFAULT_CHARSET),
                key.getBytes(Constants.DEFAULT_CHARSET),
                iv.getBytes(Constants.DEFAULT_CHARSET)));
    }

    /**
     * 解密方法
     *
     * @param ciphertext 要解密的字符串
     * @param key        解密密钥
     * @param iv         加密向量
     */
    public static byte[] decode(byte[] ciphertext, byte[] key, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_7_PADDING, BC);
            cipher.init(Cipher.DECRYPT_MODE, init(key), new IvParameterSpec(iv));
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            logger.error("ciphertext {}", ciphertext);
            logger.error("key {}", key);
            logger.error("iv {}", iv);
            logger.error("decrypt 异常", e);
            throw new RuntimeException(e);
        }
    }
}
