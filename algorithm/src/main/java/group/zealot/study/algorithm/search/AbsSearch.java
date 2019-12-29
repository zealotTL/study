package group.zealot.study.algorithm.search;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static group.zealot.study.algorithm.util.NumbersUtil.NUMBERS_DEFULT_VALUE;
import static group.zealot.study.algorithm.util.Utils.*;

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
        verification(numbers, key);

        this.numbers = numbers;
        this.key = key;
        length = this.numbers.length;
        logger.debug("key：" + key + " 原始：" + JSONObject.toJSONString(this.numbers));

        keyPoints = new int[10];
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

    private void verification(int[] numbers, int key) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("传入数组数据不合法");
        }
        if (key == NUMBERS_DEFULT_VALUE) {
            throw new RuntimeException("传入key不允许");
        }
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
    protected boolean compareKey(int i) {
        contrastNumber++;
        return NumberUtil.compare(numbers[i], key) == 0;
    }

    protected boolean compareValue(int a, int b) {
        contrastNumber++;
        return NumberUtil.compare(a, b) == 0;
    }

    /**
     * 将下标添加到 数组keyPoints中
     *
     * @param keyPoint 符合条件的下标
     */
    protected void addKeyPoint(int keyPoint) {
        keyPoints = NumbersUtil.addValue(keyPoints, keyPoint);
    }

}
