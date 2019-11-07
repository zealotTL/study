package group.zealot.study.algorithm.sort;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 冒泡排序
 */
@Component
public class MaoPaoSort {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private int contrastNumber = 0;
    private int exchangeNumber = 0;

    public void sort() {
        int length = 4;
        int[] numbers = create(length);
        logger.info("原始：" + JSONObject.toJSONString(numbers));

        int num = 1;
        while (num < length) {
            for (int i = 0; i < length - num; i++) {
                contrastAndExchange(numbers, i);
            }
            num++;
            logger.info((num - 1) + "次：" + JSONObject.toJSONString(numbers));
        }

        logger.info("结果：" + JSONObject.toJSONString(numbers));
        logger.info("总比较次数：" + contrastNumber);
        logger.info("总交换次数：" + exchangeNumber);
    }

    /**
     * 随机生成length长度的数组，随机数从0-99之间
     */
    private int[] create(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        return numbers;
    }

    /**
     * 此方法比较A(number[i])、B(numer[i+1])两个元素，若A元素大于B元素则交换位置
     *
     * @param numbers 数组
     * @param i       A元素的下标 A=number[i]
     */
    private void contrastAndExchange(int[] numbers, int i) {
        if (i > numbers.length - 1) {
            throw new RuntimeException("数组越界i:" + i + " numbers.length:" + numbers.length);
        }

        int a = numbers[i];
        int b = numbers[i + 1];
        if (a > b) {
            numbers[i + 1] = a;
            numbers[i] = b;

            exchangeNumber++;
        }

        contrastNumber++;
    }
}
