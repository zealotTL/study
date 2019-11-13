package group.zealot.study.algorithm.search;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.util.NumbersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsSearch implements Search {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算比较次数
     */
    protected int contrastNumber = 0;
    /**
     * 目标数组
     */
    protected int[] numbers = null;
    /**
     * 目标数组长度
     */
    protected int length = -1;

    @Override
    public int searchMax(int[] numbers) {
        this.numbers = numbers;
        length = this.numbers.length;
        logger.debug("原始：" + JSONObject.toJSONString(this.numbers));

        int maxPoint = doSearchMax();

        logger.debug("结果：Max：numbers[" + maxPoint + "]" + this.numbers[maxPoint]);
        logger.debug("总比较次数：" + contrastNumber);
        return maxPoint;
    }

    /**
     * 各搜索算法的核心
     */
    abstract protected int doSearchMax();

    /**
     * 此方法比较A(numbers[i])、B(numbers[j])两个元素，A > B 则返回true（若A==B，则返回true）
     *
     * @param i A元素的下标 A=numbers[i]
     * @param j B元素的下标 B=numbers[j]
     */
    protected boolean iGreaterJ(int i, int j) {
        contrastNumber++;
        return NumbersUtil.iGreaterJ(this.numbers, i, j);
    }

}
