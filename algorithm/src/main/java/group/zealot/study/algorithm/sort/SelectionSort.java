package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

@Component
public class SelectionSort extends AbsSort {
    @Override
    public void doSort() {
        int num = 1;
        for (int i = 0; i < length - 1; i++) {
            contrastAndExchange(i);
            num++;
            logNumbers(num);
        }
    }


    private void contrastAndExchange(int i) {
        int index = i;
        int j;
        for (j = i + 1; j < length; j++) {
            if (iGreaterJ(index, j)) {
                index = j;
            }
        }
        if (index != i) {
            exchange(index, i);
        }
    }

}
