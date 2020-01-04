package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * 查询工具类
 * <p>
 * 打印的cost为纳秒，此为参考（和JVM申请内存，以及各种优化相关）
 * 若要相对准确的，可以多次【运行】单个方法的排序，然后取平均值。再以平均值对比
 */
@Component
public class SearchUtil {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 对算法进行详细检测
     * 生成三组数据，分别长度位100,1000,10000；key是2位以内的数
     * 对算法组进行检测
     *
     * @param list 算法对象队列
     */
    public void checkCareful(List<Search> list) {
        List<int[]> numbersList = new ArrayList<>();
        numbersList.add(NumbersUtil.create(100));
        numbersList.add(NumbersUtil.create(1000));
        numbersList.add(NumbersUtil.create(10000));
        int key = NumberUtil.getRandom(2);
        list.forEach(sort -> {
            long start = System.nanoTime();
            logger.info("开始检测" + sort.getClass());
            if (checkCareful(sort, NumbersUtil.cloneNumbersList(numbersList), key)) {
                logger.info("检测通过:" + sort.getClass());
            }
            long end = System.nanoTime();
            logger.info("cost :" + (end - start));
        });
    }

    /**
     * 用多组数据对算法进行检测
     *
     * @param search      算法对象
     * @param numbersList 目标数组队列
     * @param key         key
     */
    private boolean checkCareful(Search search, List<int[]> numbersList, int key) {
        try {
            numbersList.forEach(numbers -> {
                //检测100位短序列排序情况
                int i = 0;
                int length = numbers.length;
                int times = length <= 100 ? 100 : length <= 1000 ? 10 : 3;
                while (i < times) {
                    boolean fg = check(search, numbers, key);
                    if (!fg) {
                        throw new RuntimeException("测试不通过，中断测试");
                    }
                    i++;
                }
            });
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    /**
     * 简单检查搜索算法是否正确（随机生成长度1000的数组【随机数因子10】和2位数内的key）
     *
     * @param search 算法对象
     */
    public boolean check(Search search) {
        return check(search, NumbersUtil.create(1000, 100), NumberUtil.getRandom(2));
    }

    /**
     * 检查搜索算法是否适用此numbers数组和key
     *
     * @param search  算法对象
     * @param numbers 目标数组
     * @param key     key
     */
    private boolean check(Search search, int[] numbers, int key) {
        int[] keyPoints = search.searchKey(numbers, key);
        int[] ans = keyPoints(numbers, key);
        boolean fg = NumbersUtil.contrastNumbers(ans, keyPoints);
        logger.debug("搜索检测：" + (fg ? "通过" : "不通过") + search.getClass());
        if (!fg) {
            logger.error("搜索检测不通过" + search.getClass());
            if (ans != null) {
                logger.error("正确结果集：" + JSONObject.toJSONString(ans));
            } else {
                logger.error("正确结果集：空");
            }
            if (keyPoints != null) {
                logger.error("算法结果集：" + JSONObject.toJSONString(keyPoints));
            } else {
                logger.error("算法结果集：空");
            }
        }
        return fg;
    }

    /**
     * 采用依次对比的方式，获取keyPoints
     *
     * @param numbers 目标数组
     * @param key     key
     */
    private int[] keyPoints(int[] numbers, int key) {
        int[] keyPoints = new int[10];
        NumbersUtil.initNumbers(keyPoints);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                keyPoints = NumbersUtil.addValue(keyPoints, i);
            }
        }
        keyPoints = NumbersUtil.clean(keyPoints);
        return keyPoints;
    }


}
