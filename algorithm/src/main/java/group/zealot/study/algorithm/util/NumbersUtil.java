package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * int数组操作类
 * 1、默认元素值为 NUMBERS_DEFULT_VALUE
 * 2、方法功能的实现，均排除默认元素
 * 3、自动扩容
 *
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
@Component
public class NumbersUtil {
    public static final int NUMBERS_DEFULT_VALUE = Integer.MIN_VALUE;
    private static final int D = 10;//数组扩容时，每次增加的长度

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 随机生成length长度的数组，随机数从[0,length * 10)之间
     */
    public int[] create(int length) {
        return create(length, length * 10);
    }

    public int[] create(int length, int random) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * random);
        }
        return numbers;
    }

    /**
     * 交换A(numbers[i])、B(numbers[j])两个元素空间
     *
     * @param numbers 目标数组
     * @param i       A元素的下标 A=numbers[i]
     * @param j       B元素的下标 B=numbers[j]
     */
    public void exchange(int[] numbers, int i, int j) {
        if (i == j) {
            return;
        }
        int a = numbers[i];
        int b = numbers[j];

        numbers[j] = a;
        numbers[i] = b;
    }

    /**
     * 判断两个数组元素是否一致
     *
     * @param A 数组A
     * @param B 数组B
     */
    public boolean contrastNumbers(int[] A, int[] B) {
        if (A == null && B == null) {
            return true;
        }
        if (A != null && B != null && A.length == B.length) {
            return aContainsB(A, B);
        }
        return false;
    }

    /**
     * 判断数组B中的元素是否都在A中
     *
     * @param A 数组A
     * @param B 数组B
     */
    public boolean aContainsB(int[] A, int[] B) {
        for (int b : B) {
            boolean fg = false;
            for (int a : A) {
                if (a == b) {
                    fg = true;
                    break;
                }
            }
            if (!fg) {
                logger.error("aContainsB false，B中：" + b + " A[]：" + JSONObject.toJSONString(A));
                return false;
            }
        }
        return true;
    }


    /**
     * 初始化结果集
     */
    public void initNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = NUMBERS_DEFULT_VALUE;
        }
    }

    /**
     * 添加元素至数组尾部
     *
     * @param numbers 数组
     * @param value   元素
     */
    public int[] addValue(int[] numbers, int value) {
        int length = getLength(numbers);
        if (length == numbers.length) {
            numbers = dilatation(numbers);
        }
        numbers[length] = value;
        return numbers;
    }

    public int getLength(int[] numbers) {
        int i = 0;
        for (int item : numbers) {
            i++;
            if (item == NUMBERS_DEFULT_VALUE) {
                break;
            }
        }
        return i;
    }

    public boolean isFull(int[] numbers) {
        for (int item : numbers) {
            if (item == NUMBERS_DEFULT_VALUE) {
                return false;
            }
        }
        return true;
    }

    private int[] dilatation(int[] oldNumbers) {
        int[] newNumbers = new int[oldNumbers.length + D];
        System.arraycopy(oldNumbers, 0, newNumbers, 0, oldNumbers.length);
        for (int i = 0; i < oldNumbers.length; i++) {
            newNumbers[i] = oldNumbers[i];
        }
        return newNumbers;
    }

    /**
     * 返回一个新数组或null，只存在有用元素（去除默认元素）
     *
     * @param numbers 目标数组
     */
    public int[] clean(int[] numbers) {
        int end = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == NUMBERS_DEFULT_VALUE) {
                end = i;
                break;
            }
        }
        if (end == 0) {
            return null;
        }
        int[] result = new int[end];
        for (int i = 0; i < result.length; i++) {
            result[i] = numbers[i];
        }
        return result;
    }
}
