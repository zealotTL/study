package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
@Component
public class NumbersUtil {
    private static final int numbers_defult_value = Integer.MIN_VALUE;

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
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，返回最大的元素（若A==B，则返回i）
     *
     * @param numbers 目标数组
     * @param i       A元素的下标 A=numbers[i]
     * @param j       B元素的下标 B=numbers[j]
     */
    public int contrastReturnMax(int[] numbers, int i, int j) {
        if (i > numbers.length || j > numbers.length || i < 0 || j < 0) {
            throw new RuntimeException("数组越界i:" + i + " j" + j + " numbers.length:" + numbers.length);
        }

        int a = numbers[i];
        int b = numbers[j];

        if (NumberUtil.contrastReturnMax(a, b) == a) {
            return i;
        } else {
            return j;
        }
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，A > B 则返回true（若A==B，则返回true）
     *
     * @param numbers 目标数组
     * @param i       A元素的下标 A=numbers[i]
     * @param j       B元素的下标 B=numbers[j]
     */
    public boolean iGreaterJ(int[] numbers, int i, int j) {
        if (i > numbers.length || j > numbers.length || i < 0 || j < 0) {
            throw new RuntimeException("数组越界i:" + i + " j" + j + " numbers.length:" + numbers.length);
        }
        return NumberUtil.contrastReturnMax(numbers[i], numbers[j]) == numbers[i];
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
                if (NumberUtil.contrast(b, a)) {
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
     * 初始化结果集（内容赋值key_points_defult_value）
     */
    public void initNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers_defult_value;
        }
    }

    /**
     * 添加符合条件的下标进结果集
     *
     * @param numbers 结果集数组
     * @param value   待添加的keyPoint
     */
    public void addValue(int[] numbers, int value) {
        for (int i = 0; i < numbers.length; i++) {
            if (NumberUtil.contrast(numbers[i], numbers_defult_value)) {
                numbers[i] = value;
                return;
            }
        }
        throw new RuntimeException("numbers 越界" + JSONObject.toJSONString(numbers));
    }

    /**
     * 剔除numbers数组中默认元素（默认第一个numbers_defult_value往后都是默认元素）
     * 如果全是默认元素，则返回null
     *
     * @param numbers 目标数组
     */
    public int[] clean(int[] numbers) {
        int end = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            if (NumberUtil.contrast(numbers[i], numbers_defult_value)) {
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
