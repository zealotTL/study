package group.zealot.study.algorithm.util;

/**
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
public class NumbersUtil {
    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，返回最大的元素（若A==B，则返回i）
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    public static int contrastReturnMax(int[] numbers, int i, int j) {
        if (i > numbers.length || j > numbers.length || i < 0 || j < 0) {
            throw new RuntimeException("数组越界i:" + i + " j" + j + " numbers.length:" + numbers.length);
        }

        int a = numbers[i];
        int b = numbers[j];
        int max;
        if (a >= b) {
            max = i;
        } else {
            max = j;
        }
        return max;
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，A > B 则返回true（若A==B，则返回true）
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    public static boolean iGreaterJ(int[] numbers, int i, int j) {
        return contrastReturnMax(numbers, i, j) == i;
    }

    /**
     * 交换A(numbers[i])、B(numbers[j])两个元素空间
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    public static void exchange(int[] numbers, int i, int j) {
        if (i == j) {
            return;
        }
        int a = numbers[i];
        int b = numbers[j];

        numbers[j] = a;
        numbers[i] = b;
    }
}
