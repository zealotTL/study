package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * 打印的cost为纳秒，此为参考（和JVM申请内存，以及各种优化相关）
 * 若要相对准确的，可以多次【运行】单个方法的排序，然后取平均值。再以平均值对比
 *
 * @author zealot
 * @date 2019/11/16 11:43
 */
@Component
public class SortUtil {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void checkSortCareful(List<Sort> list) {
        List<int[]> numbersList = new ArrayList<>();
        numbersList.add(NumbersUtil.create(100));
        numbersList.add(NumbersUtil.create(1000));
        numbersList.add(NumbersUtil.create(10000));
        list.forEach(sort -> {
            long start = System.nanoTime();
            logger.info("开始检测" + sort.getClass());
            if (checkSortCareful(sort, cloneNumbersList(numbersList))) {
                logger.info("检测通过:" + sort.getClass());
            }
            long end = System.nanoTime();
            logger.info("cost :" + (end - start));
        });
    }

    private List<int[]> cloneNumbersList(List<int[]> numbersList) {
        List<int[]> cloneNumbersList = new ArrayList<>();
        numbersList.forEach(numbers -> cloneNumbersList.add(numbers.clone()));
        return cloneNumbersList;
    }

    public boolean checkSortCareful(Sort sort, List<int[]> numbersList) {
        try {
            numbersList.forEach(numbers -> {
                //检测100位短序列排序情况
                int i = 0;
                int length = numbers.length;
                int times = length <= 100 ? 100 : length <= 1000 ? 10 : 3;
                while (i < times) {
                    boolean fg = checkSort(sort, numbers);
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

    public void checkSort(List<Sort> list) {
        int[] numbers = NumbersUtil.create(10);
        list.forEach(sort -> {
            long start = System.nanoTime();
            logger.info("开始检测" + sort.getClass());
            if (checkSort(sort, numbers.clone())) {
                logger.info("检测通过:" + sort.getClass());
            }
            long end = System.nanoTime();
            logger.info("cost :" + (end - start));
        });
    }

    public boolean checkSort(Sort sort, int[] numbers) {
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
