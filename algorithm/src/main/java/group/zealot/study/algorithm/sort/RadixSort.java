package group.zealot.study.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.util.NumberUtil;
import org.springframework.stereotype.Component;

/**
 * 基数排序
 */
@Component
public class RadixSort extends AbsSort {
    private static final int defult_length = 10;//0~9 10个数
    private static final int bit_length = 10;

    @Override
    public void doSortMinToMax() {
        for (int bit = 1; bit <= bit_length; bit++) {
            sortBit(bit);
        }
    }

    private void sortBit(int bit) {
        int[] newNumber = new int[length];

        int value = 0;
        int index = 0;
        while (value < defult_length) {
            for (int i = 0; i < length; i++) {
                if (value == NumberUtil.getBitValue(numbers[i], bit)) {
                    newNumber[index] = numbers[i];
                    index++;
                }
            }
            value++;
        }
        numbers = newNumber;
    }

}
