package group.zealot.study.algorithm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zealotTL
 * @date 2019-11-14 17:18
 */
@Component
public class NumberUtil {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 返回整数i第bit位上数字（bit =1，则返回个位上的数字），若没有则默认为0
     *
     * @param i   目标整数
     * @param bit 比较第bit位
     */
    public int getBitValue(int i, int bit) {
        if (bit <= 0) {
            throw new RuntimeException("bit 小于等于 0");
        }
        String str = i + "";
        if (str.length() - bit < 0) {
            return 0;
        }
        return str.charAt(str.length() - bit) - (int) '0'/*48*/;//ASCII码 0->48
    }

    public int getLength(int i) {
        int length = 1;
        while ((i = i / 10) > 0) {
            length++;
        }
        return length;
    }

    public int getLength2(int i) {
        return (i + "").length();
    }

    /**
     * 返回一个随机数，数位长度为bit
     *
     * @param bit 随机数的长度位数
     */
    public int getRandom(int bit) {
        return (int) (Math.random() * bit * 10);
    }


    /**
     * F
     * 直接比较元素a和b，a >= b，返回true
     *
     * @param a A元素
     * @param b B元素
     */
    public int compare(int a, int b) {
        if (a > b) {
            return 1;
        } else if (a == b) {
            return 0;
        } else {
            return -1;
        }
    }
}
