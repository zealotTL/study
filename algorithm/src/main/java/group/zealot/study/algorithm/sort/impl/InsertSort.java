package group.zealot.study.algorithm.sort.impl;

import group.zealot.study.algorithm.sort.AbsSort;
import org.springframework.stereotype.Component;

@Component
public class InsertSort extends AbsSort {
    @Override
    public void doSortMinToMax() {
        for (int i = 1; i < length; ++i) {
            int value = numbers[i];
            int j = i - 1;
            while (j >= 0 && numIGreaterJ(numbers[j], value)) {
                exchange(j + 1, j);
                --j;
            }
            logNumbers(i);
        }
    }
}
