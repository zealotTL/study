package group.zealot.study.algorithm;

import group.zealot.study.algorithm.sort.*;
import group.zealot.study.algorithm.util.SortUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class CheckSort {
    @Autowired
    ApplicationContext context;

    @Test
    public void checkCareful() {
        Map<String, Sort> map = context.getBeansOfType(Sort.class);
        //跳转log级别为INFO
        SortUtil.checkSortCareful(new ArrayList<>(map.values()));
    }
}
