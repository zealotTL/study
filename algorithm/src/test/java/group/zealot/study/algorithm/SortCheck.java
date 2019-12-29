package group.zealot.study.algorithm;

import ch.qos.logback.classic.Level;
import group.zealot.study.algorithm.sort.*;
import group.zealot.study.algorithm.sort.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class SortCheck {
    @Autowired
    ApplicationContext context;

    @Before
    public void setLogLevel() {
        Run.setLogLevel(Level.INFO);// checkCareful()打开
//        Run.setLogLevel(Level.TRACE);// contrast()打开
    }

    @Test
    public void checkCareful() {
        Map<String, Sort> map = context.getBeansOfType(Sort.class);
        SortUtil.checkCareful(new ArrayList<>(map.values()));
    }

    /**
     * 若想对比时间，运行排序多次，去掉最高值最低值，然后取平均值
     */
    @Test
    public void contrast() {
        List<Sort> sortList = new ArrayList<>();
        sortList.add(context.getBean(InertSort.class));
        sortList.add(context.getBean(InertSort.class));
        sortList.add(context.getBean(InertSort.class));
        sortList.add(context.getBean(InertSort.class));

        sortList.add(context.getBean(InsertSort.class));
        sortList.add(context.getBean(InsertSort.class));
        sortList.add(context.getBean(InsertSort.class));
        sortList.add(context.getBean(InsertSort.class));
        SortUtil.check(sortList);
    }

}
