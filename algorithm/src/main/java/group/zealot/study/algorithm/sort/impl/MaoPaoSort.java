package group.zealot.study.algorithm.sort.impl;

import group.zealot.study.algorithm.sort.AbsSort;
import org.springframework.stereotype.Component;

/**
 * 冒泡排序
 *
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
@Component
public class MaoPaoSort extends AbsSort {

    @Override
    public void doSortMinToMax() {
        int num = 1;
        while (num < length) {
            //每一轮for循环，则找出numbers[0,length-num-1]的最大元素，交换位置至numbers[length-num-1]
            for (int i = 0; i < length - num; i++) {
                contrastAndExchange(i, i + 1);
            }
            num++;
            logNumbers(num - 1);
        }
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，若A元素大于B元素则交换位置
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j A元素的下标 B=numbers[j]
     */
    private void contrastAndExchange(int i, int j) {
        if (iGreaterJ(i, j)) {
            exchange(i, j);
        }
    }
}
