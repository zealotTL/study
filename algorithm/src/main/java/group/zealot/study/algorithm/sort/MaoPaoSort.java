package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

/**
 * 冒泡排序
 */
@Component
public class MaoPaoSort extends AbsSort {

    @Override
    public void doSort(int[] numbers) {
        int length = numbers.length;
        int num = 1;
        while (num < length) {
            for (int i = 0; i < length - num; i++) {
                contrastAndExchange(numbers, i);
            }
            num++;
            logNumbers(numbers, num);
        }
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[i+1])两个元素，若A元素大于B元素则交换位置
     *
     * @param numbers 数组
     * @param i       A元素的下标 A=numbers[i]
     */
    private void contrastAndExchange(int[] numbers, int i) {
        int max = contrast(numbers, i, i + 1);
        if (max == i) {
            exchange(numbers, i, i + 1);
        }
    }
}
