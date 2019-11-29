package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

@Component
public class InsertSort extends AbsSort {
    @Override
    public void doSortMinToMax() {
        if (length <= 1) {
            return;
        }
        for (int i = 1; i < length; ++i) {
            int value = numbers[i];
            int j = i - 1;
            while (j >= 0 && numIGreaterJ(numbers[j],value)) {
                exchange(j + 1,j);
                --j;
            }
            numbers[j + 1] = value;
            logNumbers(i);
        }
    }
}
