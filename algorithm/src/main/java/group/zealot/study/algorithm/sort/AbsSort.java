package group.zealot.study.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsSort implements Sort {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算比较次数
     */
    protected int contrastNumber = 0;
    protected int exchangeNumber = 0;
    protected int[] numbers = null;
    protected int length = -1;


    @Override
    public void sort(int[] numbers) {
        this.numbers = numbers;
        length = numbers.length;

        logger.debug("原始：" + JSONObject.toJSONString(this.numbers));

        doSort();

        logger.debug("结果：" + JSONObject.toJSONString(this.numbers));
        logger.debug("总比较次数：" + contrastNumber);
        logger.debug("总交换次数：" + exchangeNumber);
    }

    /**
     * 各排序算法的核心
     */
    public abstract void doSort();

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，A > B 则返回true（若A==B，则返回true）
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    protected boolean iGreaterJ(int i, int j) {
        return contrastReturnMax(i, j) == i;
    }

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，返回最大的元素（若A==B，则返回i）
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    protected int contrastReturnMax(int i, int j) {
        if (i > length || j > length || i < 0 || j < 0) {
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

    /**
     * 交换A(numbers[i])、B(numbers[j])两个元素空间
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    protected void exchange(int i, int j) {
        if (i == j) {
            return;
        }
        int a = numbers[i];
        int b = numbers[j];

        numbers[j] = a;
        numbers[i] = b;

        exchangeNumber++;
    }

    protected void logNumbers(int num) {
        logger.debug(num + "次：" + JSONObject.toJSONString(numbers));
    }
}
