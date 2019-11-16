package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.sort.AbsSort;
import group.zealot.study.algorithm.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zealot
 * @date 2019/11/16 11:43
 */
public class SortUtil {

    protected static Logger logger = LoggerFactory.getLogger(SortUtil.class);

    public static void checkSortCareful(List<Sort> list) {
        list.forEach(sort -> {
            logger.info("开始检测" + sort.getClass());
            if (checkSortCareful(sort)) {
                logger.info("检测通过:" + sort.getClass());
            }
            logger.info("");
        });
    }

    public static boolean checkSortCareful(Sort sort) {
        {
            //检测100位短序列排序情况
            int i = 0;
            while (i < 100) {
                int[] numbers = create(100);
                boolean fg = checkSort(sort, numbers);
                if (!fg) {
                    return false;
                }
                i++;
            }
        }
        {
            //检测1000位中长序列排序情况
            int i = 0;
            while (i < 10) {
                int[] numbers = create(1000);
                boolean fg = checkSort(sort, numbers);
                if (!fg) {
                    return false;
                }
                i++;
            }
        }
        {
            //检测10000位长序列排序情况
            int i = 0;
            while (i < 3) {
                int[] numbers = create(10000);
                boolean fg = checkSort(sort, numbers);
                if (!fg) {
                    return false;
                }
                i++;
            }
        }
        return true;

    }

    /**
     * 随机生成length长度的数组，随机数从0-99之间
     */
    public static int[] create(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * length * 10);
        }
        return numbers;
    }

    public static boolean checkSort(Sort sort) {
        return checkSort(sort, create(10));
    }

    public static boolean checkSort(Sort sort, int[] numbers) {
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
            logger.error("排序不通过" + sort.getClass() + ":" + JSONObject.toJSONString(result));
            return false;
        }
        return fg;
    }
}
