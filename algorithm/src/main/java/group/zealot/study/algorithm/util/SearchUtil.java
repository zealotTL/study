package group.zealot.study.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.search.Search;
import group.zealot.study.algorithm.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static group.zealot.study.algorithm.util.Utils.*;

@Component
public class SearchUtil {


    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void checkSearchCareful(List<Search> list) {
        list.forEach(search -> {
            logger.info("开始检测" + search.getClass());
            if (checkSearchCareful(search)) {
                logger.info("检测通过:" + search.getClass());
            }
            logger.info("");
        });
    }

    /**
     * 多次检测搜索算法是否正确（随机生成长度1000的数组【随机数因子10】和2位数内的key）
     */
    public boolean checkSearchCareful(Search search) {
        {
            //检测10000位长序列排序情况
            int i = 0;
            while (i < 100) {
                boolean fg = checkSearch(search, NumbersUtil.create(1000, 100), NumberUtil.getRandom(2));
                if (!fg) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    /**
     * 简单检查搜索算法是否正确（随机生成长度1000的数组【随机数因子10】和2位数内的key）
     */
    public boolean checkSearch(Search search) {
        return checkSearch(search, NumbersUtil.create(1000, 100), NumberUtil.getRandom(2));
    }

    /**
     * 检查搜索算法是否适用此numbers数组和key
     *
     * @param search  算法对象
     * @param numbers 目标数组
     * @param key     key
     */
    public boolean checkSearch(Search search, int[] numbers, int key) {
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
    public int[] keyPoints(int[] numbers, int key) {
        int[] keyPoints = new int[numbers.length];
        NumbersUtil.initNumbers(keyPoints);
        for (int i = 0; i < numbers.length; i++) {
            if (NumberUtil.contrast(numbers[i], key)) {
                NumbersUtil.addValue(keyPoints, i);
            }
        }
        keyPoints = NumbersUtil.clean(keyPoints);
        return keyPoints;
    }


}
