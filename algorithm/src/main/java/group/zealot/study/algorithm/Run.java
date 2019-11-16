package group.zealot.study.algorithm;

import group.zealot.study.algorithm.sort.AbsSort;
import group.zealot.study.algorithm.sort.RadixSort;
import group.zealot.study.algorithm.sort.Sort;
import group.zealot.study.algorithm.util.SortUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zealotTL
 * @date 2019-11-14 09:13
 */
@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);
        check(context.getBean(RadixSort.class));
    }

    public static void check(Sort sort) {
        //跳转log级别为DEBUG
        SortUtil.checkSort(sort);
    }
}

