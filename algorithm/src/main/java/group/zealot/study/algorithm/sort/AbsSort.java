package group.zealot.study.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsSort implements Sort {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算比较次数
     */
    protected int contrastNumber = 0;
    protected int exchangeNumber = 0;


    @Override
    public void sort(int[] numbers) {
        logger.info("原始：" + JSONObject.toJSONString(numbers));

        doSort(numbers);

        logger.info("结果：" + JSONObject.toJSONString(numbers));
        logger.info("总比较次数：" + contrastNumber);
        logger.info("总交换次数：" + exchangeNumber);

        Run.check(numbers);
    }

    /**
     * 各排序算法的核心
     */
    public abstract void doSort(int[] numbers);


    /**
     * 此方法比较A(number[i])、B(numer[j])两个元素，返回最大的元素（若A==B，则返回i）
     *
     * @param numbers 数组
     * @param i       A元素的下标 A=number[i]
     * @param j       B元素的下标 B=number[j]
     */
    protected int contrast(int[] numbers, int i, int j) {
        if (i > numbers.length || j > numbers.length || i < 0 || j < 0) {
            throw new RuntimeException("数组越界i:" + i + " j" + j + " numbers.length:" + numbers.length);
        }

        int a = numbers[i];
        int b = numbers[j];
        int max;
        if (a >= b) {
            max = i;
        } else {
            max = j;
        }
        contrastNumber++;
        return max;
    }
}
