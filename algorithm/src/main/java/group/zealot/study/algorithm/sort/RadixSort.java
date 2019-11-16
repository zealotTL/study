package group.zealot.study.algorithm.sort;

import group.zealot.study.algorithm.util.NumberUtil;
import org.springframework.stereotype.Component;

/**
 * 基数排序
 */
@Component
public class RadixSort extends AbsSort {
    private static final int defult_length = 10;//0~9 10个数
    private static final int bit_length = 10;//最大10位数的整数

    @Override
    public void doSortMinToMax() {
        for (int bit = 1; bit <= bit_length; bit++) {
            sortBit(bit);
        }
    }

    /**
     * 对数组numbers各元素，按第bit位排序（bit = 1表示对个位上的数字进行排序）
     *
     * @param bit 位数
     */
    private void sortBit(int bit) {
        int[] newNumber = new int[length];

        int value = 0;
        int index = 0;
        while (value < defult_length) {//bit上的数从0开始比较到9
            for (int i = 0; i < length; i++) {
                if (value == NumberUtil.getBitValue(numbers[i], bit)) {//对数组所有数据进行判断，若bit位上数字与value相等，则优先放入数组
                    newNumber[index] = numbers[i];
                    index++;
                    contrastNumber++;
                    exchangeNumber++;
                }
            }
            value++;
        }
        numbers = newNumber;
    }
}
