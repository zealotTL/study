package group.zealot.study.algorithm;

import group.zealot.study.algorithm.sort.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);
        context.getBean(MaoPaoSort.class).sort(create(10));
        context.getBean(SelectionSort.class).sort(create(10));
        context.getBean(QuickSort.class).sort(create(10));
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

    public static void check(int[] numbers) {
        boolean fg = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                fg = false;
                logger.error("错误：number[" + i + "]：" + numbers[i] + " > number[" + (i + 1) + "]：" + numbers[i + 1]);
            }
        }
        logger.info("排序检测：" + (fg ? "通过" : "不通过"));
    }
}
