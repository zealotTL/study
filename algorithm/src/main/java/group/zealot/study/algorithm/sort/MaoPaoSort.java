package group.zealot.study.algorithm.sort;


import com.alibaba.fastjson.JSONObject;
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
            logger.info((num - 1) + "次：" + JSONObject.toJSONString(numbers));
        }
    }

    /**
     * 此方法比较A(number[i])、B(numer[i+1])两个元素，若A元素大于B元素则交换位置
     *
     * @param numbers 数组
     * @param i       A元素的下标 A=number[i]
     */
    private void contrastAndExchange(int[] numbers, int i) {
        int max = contrast(numbers, i, i + 1);
        if (max == i) {
            int a = numbers[i];
            int b = numbers[i + 1];

            numbers[i + 1] = a;
            numbers[i] = b;

            exchangeNumber++;
        }
    }
}
