package group.zealot.study.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SelectionSort extends AbsSort {
    @Override
    public void doSort(int[] numbers) {
        int length = numbers.length;
        int num = 1;
        for (int i = 0; i < length - 1; i++) {
            contrastAndExchange(numbers, length, i);
            num++;
            logger.info((num - 1) + "次：" + JSONObject.toJSONString(numbers));
        }
    }


    private void contrastAndExchange(int[] numbers, int length, int i) {
        int index = i;
        int j;
        for (j = i + 1; j < length; j++) {
            int max = contrast(numbers, j, index);
            if (max == index) {
                index = j;
            }
        }
        if (index != i) {
            int tmp = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = tmp;
            exchangeNumber++;
        }
    }

}
