package group.zealot.study.algorithm;

import group.zealot.study.algorithm.search.*;
import group.zealot.study.algorithm.sort.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

import static group.zealot.study.algorithm.util.Utils.*;

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
        checkSort(context.getBean(InsertSort.class),context.getBean(InertSort.class));
//        checkSearch(context.getBean(BinaryTreeSearch.class));
    }

    public static void checkSort(Sort sort) {
        //跳转log级别为DEBUG
        SortUtil.checkSort(sort);
    }

    public static void checkSort(Sort... sorts) {
        //跳转log级别为DEBUG
        SortUtil.checkSort(Arrays.asList(sorts));
    }

    public static void checkSearch(Search search) {
        SearchUtil.checkSearch(search);
    }
}

