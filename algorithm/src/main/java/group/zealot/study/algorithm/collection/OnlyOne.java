package group.zealot.study.algorithm.collection;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 给你一个长度为N的数组，其中只有一个数字出现了奇数次，其他均出现偶数次，问如何使用优秀的时空复杂度快速找到这个数字。
 */
@Component
public class OnlyOne {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public int run(int[] numbers) {
        logger.trace("numbers：" + JSONObject.toJSONString(numbers));

        int[] size = new int[10];
        for (int i = 0; i < 10; i++) {
            size[i] = 0;
        }
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            size[numbers[i]]++;
        }
        logger.trace("size：" + JSONObject.toJSONString(size));
        for (int i = 0; i < 10; i++) {
            if (size[i] % 2 == 1) {
                return i;
            }
        }
        throw new RuntimeException("异常，指定数组，没有出现奇数次的数字");
    }
}
