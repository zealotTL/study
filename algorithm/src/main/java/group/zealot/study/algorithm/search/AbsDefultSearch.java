package group.zealot.study.algorithm.search;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static group.zealot.study.algorithm.util.Utils.*;

public abstract class AbsDefultSearch implements Search {

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
    /**
     * 搜索key
     */
    protected int key = -1;
    /**
     * 结果集
     */
    protected int[] keyPoints = null;


    @Override
    public int[] searchKey(int[] numbers, int key) {
        this.numbers = numbers;
        this.key = key;
        length = this.numbers.length;
        logger.debug("key：" + key + " 原始：" + JSONObject.toJSONString(this.numbers));

        keyPoints = new int[length];
        NumbersUtil.initNumbers(keyPoints);//初始化数组元素
        doSearchKey();
        keyPoints = NumbersUtil.clean(keyPoints);//清理默认元素
        if (keyPoints != null) {
            logger.debug("结果：keyPoints：" + JSONObject.toJSONString(keyPoints));
        } else {
            logger.debug("结果：keyPoints：无");
        }

        logger.debug("总比较次数：" + contrastNumber);
        return keyPoints;
    }

    /**
     * 各搜索算法的核心
     */
    abstract protected void doSearchKey();

    /**
     * 此方法比较A(numbers[i])、key)两个元素，A == key 则返回true
     *
     * @param i A元素的下标 A=numbers[i]
     */
    protected boolean contrastKey(int i) {
        contrastNumber++;
        return NumberUtil.contrast(numbers[i], key);
    }

    protected void addKeyPoint(int keyPoint) {
        NumbersUtil.addValue(keyPoints, keyPoint);
    }

}
