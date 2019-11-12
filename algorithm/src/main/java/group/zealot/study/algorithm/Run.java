package group.zealot.study.algorithm;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.algorithm.sort.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);
        List<Sort> list = new ArrayList<>();
        list.add(context.getBean(MaoPaoSort.class));
        list.add(context.getBean(SelectionSort.class));
        list.add(context.getBean(QuickSort.class));
        list.add(context.getBean(InertSort.class));
        list.forEach(sort -> {
            logger.info("开始检测" + sort.getClass());
            if (checkSort(sort)) {
                logger.info("检测通过:" + sort.getClass());
            }
            logger.info("");
        });

    }

    public static boolean checkSort(Sort sort) {
        {
            //检测10位短序列排序情况
            int i = 0;
            while (i < 3) {
                int[] numbers = create(100);
                sort.sort(numbers);
                boolean fg = check(numbers);
                if (!fg) {
                    logger.error("排序不通过" + sort.getClass() + ":" + JSONObject.toJSONString(numbers));
                    return false;
                }
                i++;
            }
        }
        {
            //检测1000位中长序列排序情况
            int i = 0;
            while (i < 3) {
                int[] numbers = create(1000);
                sort.sort(numbers);
                boolean fg = check(numbers);
                if (!fg) {
                    logger.error("排序不通过" + sort.getClass() + ":" + JSONObject.toJSONString(numbers));
                    return false;
                }
                i++;
            }
        }
        {
            //检测100000位长序列排序情况
            int i = 0;
            while (i < 3) {
                int[] numbers = create(10000);
                sort.sort(numbers);
                boolean fg = check(numbers);
                if (!fg) {
                    logger.error("排序不通过" + sort.getClass() + ":" + JSONObject.toJSONString(numbers));
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
            numbers[i] = (int) (Math.random() * 100);
        }
        return numbers;
    }

    public static boolean check(int[] numbers) {
        boolean fg = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                fg = false;
                logger.error("错误：number[" + i + "]：" + numbers[i] + " > number[" + (i + 1) + "]：" + numbers[i + 1]);
            }
        }
        logger.debug("排序检测：" + (fg ? "通过" : "不通过"));
        return fg;
    }
}
