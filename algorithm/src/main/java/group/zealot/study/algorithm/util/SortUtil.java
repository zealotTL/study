package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * @author zealot
 * @date 2019/11/16 11:43
 */
@Component
public class SortUtil {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void checkSortCareful(List<Sort> list) {
        list.forEach(sort -> {
            logger.info("开始检测" + sort.getClass());
            if (checkSortCareful(sort)) {
                logger.info("检测通过:" + sort.getClass());
            }
            logger.info("");
        });
    }

    public boolean checkSortCareful(Sort sort) {
        {
            //检测100位短序列排序情况
            int i = 0;
            while (i < 100) {
                int[] numbers = NumbersUtil.create(100);
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
                int[] numbers = NumbersUtil.create(1000);
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
                int[] numbers = NumbersUtil.create(10000);
                boolean fg = checkSort(sort, numbers);
                if (!fg) {
                    return false;
                }
                i++;
            }
        }
        return true;

    }


    public boolean checkSort(Sort sort) {
        return checkSort(sort, NumbersUtil.create(10));
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
