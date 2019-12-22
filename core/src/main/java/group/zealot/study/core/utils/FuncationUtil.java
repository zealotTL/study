package group.zealot.study.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

public class FuncationUtil {
    public static final int PREVIOUS_METHOD = 1;//0 为本方法名称 ， 1 为上一个调用

    public static String getHostName() {
        try {
            return getInetAddress().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException("UnknownHostException", e);
        }
    }

    public static String getHostAddress() {
        try {
            return getInetAddress().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("UnknownHostException", e);
        }
    }

    public static InetAddress getInetAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    public static StackTraceElement[] getStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    public static String getThisMethodName() {
        return getStackTrace()[PREVIOUS_METHOD].getMethodName();
    }

    /**
     * 毫秒级别
     */
    public static long createTime() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 创建 size 位的随机数
     */
    public static long createRandom(int size) {
        return ((Double) (Math.random() * Math.pow(10, size))).longValue();
    }

    public static void assertIsNull(Object value, String message) {
        if (value != null) {
            throw new RuntimeException(message);
        }
    }

    public static void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new RuntimeException(message);
        }
    }
}
