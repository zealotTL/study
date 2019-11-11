package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

/**
 * 冒泡排序
 */
@Component
public class MaoPaoSort extends AbsSort {

    @Override
    public void doSort() {
        int length = numbers.length;
        int num = 1;
        while (num < length) {
            for (int i = 0; i < length - num; i++) {
                contrastAndExchange(i, i + 1);
            }
            num++;
            logNumbers(num);
        }
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，若A元素大于B元素则交换位置
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j A元素的下标 B=numbers[j]
     */
    private void contrastAndExchange(int i, int j) {
        int max = contrastReturnMax(i, j);
        if (max == i) {
            exchange(i, j);
        }
    }
}
