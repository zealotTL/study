package group.zealot.study.algorithm;

import ch.qos.logback.classic.Level;
import group.zealot.study.algorithm.search.Search;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

import static group.zealot.study.algorithm.util.Utils.SearchUtil;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class SearchCheck {
    @Autowired
    ApplicationContext context;

    @Before
    public void setLevel() {
        Run.setLogLevel(Level.INFO);// checkCareful()打开
//        Run.setLogLevel(Level.TRACE);
    }

    @Test
    public void checkCareful() {
        Map<String, Search> map = context.getBeansOfType(Search.class);
        //跳转log级别为INFO
        SearchUtil.checkCareful(new ArrayList<>(map.values()));
    }
}
