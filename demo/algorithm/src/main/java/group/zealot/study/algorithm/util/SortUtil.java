package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * 排序工具类
 * <p>
 * 打印的cost为纳秒，此为参考（和JVM申请内存，以及各种优化相关）
 * 若要相对准确的，可以多次【运行】单个方法的排序，然后取平均值。再以平均值对比
 *
 * @author zealot
 * @date 2019/11/16 11:43
 */
@Component
public class SortUtil {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 对算法进行详细检测
     * 生成三组数据，分别长度位100,1000,10000
     * 对算法组进行检测
     *
     * @param list 算法对象队列
     */
    public void checkCareful(List<Sort> list) {
        List<int[]> numbersList = new ArrayList<>();
        numbersList.add(NumbersUtil.create(100));
        numbersList.add(NumbersUtil.create(1000));
        numbersList.add(NumbersUtil.create(10000));
        StopWatch sw = new StopWatch();
        list.forEach(sort -> {
            logger.info("开始检测" + sort.getClass());
            sw.start("sort:" + sort.getClass());
            if (checkCareful(sort, NumbersUtil.cloneNumbersList(numbersList))) {
                logger.info("检测通过:" + sort.getClass());
            }
            sw.stop();
        });
        logger.info(sw.prettyPrint());
    }

    /**
     * 用多组数据对算法进行检测
     *
     * @param sort        算法对象
     * @param numbersList 目标数组队列
     */
    private boolean checkCareful(Sort sort, List<int[]> numbersList) {
        try {
            numbersList.forEach(numbers -> {
                //检测100位短序列排序情况
                int i = 0;
                int length = numbers.length;
                int times = length <= 100 ? 100 : length <= 1000 ? 10 : 3;
                while (i < times) {
                    boolean fg = check(sort, numbers);
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
     * 简单检查搜索算法是否正确（随机生成长度1000的数组）
     *
     * @param list 算法对象组
     */
    public void check(List<Sort> list) {
        StopWatch sw = new StopWatch();
        List<int[]> numbers = prepareData(list.size());
        for (int i = 0; i < list.size(); i++) {
            Sort sort = list.get(i);
            logger.info("开始检测" + sort.getClass());
            sw.start("sort:" + sort.getClass());
            if (check(sort, numbers.get(i))) {
                logger.info("检测通过:" + sort.getClass());
            }
            sw.stop();
        }
        logger.info(sw.prettyPrint());
    }

    private List<int[]> prepareData(int n) {
        List<int[]> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(NumbersUtil.create(10000));
        }
        return numbers;
    }

    /**
     * 简单检查搜索算法是否正确（随机生成长度1000的数组）
     *
     * @param sort    算法对象
     * @param numbers 目标数组
     */
    private boolean check(Sort sort, int[] numbers) {
        int[] result = sort.sortMinToMax(numbers);
        boolean fg = true;
        for (int i = 0; i < result.length - 1; i++) {
            if (result[i] > result[i + 1]) {
                fg = false;
                logger.error("错误：number[" + i + "]：" + result[i] + " > number[" + (i + 1) + "]：" + result[i + 1]);
            }
        }
        logger.debug("排序检测：" + (fg ? "通过" : "不通过") + sort.getClass());
        if (!fg) {
            logger.error("排序检测不通过" + sort.getClass() + ":" + JSONObject.toJSONString(result));
        }
        return fg;
    }
}
