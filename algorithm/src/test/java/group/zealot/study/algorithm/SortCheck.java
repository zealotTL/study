package group.zealot.study.algorithm;

import group.zealot.study.algorithm.sort.*;
import group.zealot.study.algorithm.sort.impl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

import static group.zealot.study.algorithm.util.Utils.*;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
//@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class SortCheck {
    @Autowired
    ApplicationContext context;

    @Test
    public void checkCareful() {
        Map<String, Sort> map = context.getBeansOfType(Sort.class);
        //跳转log级别为INFO
        SortUtil.checkSortCareful(new ArrayList<>(map.values()));
    }

    /**
     * 需要打印详细内容，需要调整log打印级别成dev
     */
    @Test
    public void contrast() {
        Sort sort1 = context.getBean(InsertSort.class);
        Sort sort2 = context.getBean(InertSort.class);

        int[] numbers = NumbersUtil.create(10000);
        int[] numbersCopy = numbers.clone();
        SortUtil.checkSort(sort1, numbers);
        SortUtil.checkSort(sort2, numbersCopy);
    }
}
