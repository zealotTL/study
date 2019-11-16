package group.zealot.study.algorithm.util;

/**
 * @author zealotTL
 * @date 2019-11-14 17:18
 */
public class NumberUtil {

    /**
     * 返回整数i第bit位上数字（bit =1，则返回个位上的数字）
     *
     * @param i   目标整数
     * @param bit 比较第bit位
     */
    public static int getBitValue(int i, int bit) {
        if (bit <= 0) {
            throw new RuntimeException("bit 小于等于 0");
        }
        String str = i + "";
        if (str.length() - bit < 0) {
            return 0;
        }
        return str.charAt(str.length() - bit) - (int) '0'/*48*/;//ASCII码 0->48
    }

    public static int getLength(int i) {
        int length = 1;
        while ((i = i / 10) > 0) {
            length++;
        }
        return length;
    }

    public static int getLength2(int i) {
        return (i + "").length();
    }
}
