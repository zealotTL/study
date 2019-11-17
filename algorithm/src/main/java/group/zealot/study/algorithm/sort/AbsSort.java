package group.zealot.study.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static group.zealot.study.algorithm.util.Utils.*;
/**
 * 排序抽象类，集成通用方法功能
 *
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
public abstract class AbsSort implements Sort {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算比较次数
     */
    protected int contrastNumber = 0;
    /**
     * 空间位置移动次数
     */
    protected int exchangeNumber = 0;


    /**
     * 目标数组
     */
    protected int[] numbers = null;

    public int[] getResult() {
        return numbers;
    }

    /**
     * 目标数组长度
     */
    protected int length = -1;


    @Override
    public int[] sortMinToMax(int[] numbers) {
        this.numbers = numbers;
        length = this.numbers.length;
        logger.debug("原始：" + JSONObject.toJSONString(this.numbers));

        doSortMinToMax();

        logger.debug("结果：" + JSONObject.toJSONString(getResult()));
        logger.debug("总比较次数：" + contrastNumber);
        logger.debug("总交换次数：" + exchangeNumber);

        return getResult();
    }

    /**
     * 各排序算法的核心
     */
    abstract public void doSortMinToMax();

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
        int maxPoint = NumbersUtil.contrastReturnMax(this.numbers, i, j);
        contrastNumber++;
        return maxPoint;
    }

    /**
     * 交换A(numbers[i])、B(numbers[j])两个元素空间
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    protected void exchange(int i, int j) {
        NumbersUtil.exchange(this.numbers, i, j);
        exchangeNumber++;
    }

    /**
     * 打印当前数组
     */
    protected void logNumbers(int num) {
        logger.debug(num + "次：" + JSONObject.toJSONString(numbers));
    }
}
