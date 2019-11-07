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
}
