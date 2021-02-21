package group.zealot.study.algorithm.sort.impl;

import group.zealot.study.algorithm.sort.AbsSort;
import org.springframework.stereotype.Component;

/**
 * 插入排序
 *
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
@Component
public class InertSort extends AbsSort {

    @Override
    public void doSortMinToMax() {
        //初始第0个元素为有序队列，后面各个元素依次插入到有序队列中，并保持有序
        int start = 0;
        for (int i = start + 1; i <= length - 1; i++) {
            contrastAndInsert(start, i - 1, i);
            logNumbers(i);
        }
    }

    /**
     * 对numbers[p]元素插入到有序队列numbers[start,end]中，并保持有序
     *
     * @param end 有序队列尾元素numbers[end]
     * @param p   待排序元素numbers[p]
     */
    private void contrastAndInsert(int start, int end, int p) {
        if (iGreaterJ(start, p)) {//number[p] <= number[i]
            insert(start, end, p);
        } else {
            for (int i = start; i <= end - 1; i++) {
                if (iGreaterJ(i + 1, p)) {//numbers[i] <= numbers[p] <= numbers[i+1]
                    insert(i + 1, end, p);
                    break;
                }
            }
        }
    }

    /**
     * 现将numbers[p]与numbers[end+1]交换位置
     * 再将numbers[p]插入到i位置（将numbers[start,end]元素都往后一位成为numbers[start+1,end+1]，并将原numbers[p]插入至numbers[start]）
     *
     * @param start 待插入位置
     * @param p     待插入元素numbers[p]
     */
    private void insert(int start, int end, int p) {
        if (p != end + 1) {
            exchange(p, end + 1);
            p = end + 1;
        }

        int temp = numbers[p];
        int i = end;
        while (i >= start) {
            numbers[i + 1] = numbers[i];
            i--;
            exchangeNumber++;
        }
        numbers[start] = temp;
    }
}
