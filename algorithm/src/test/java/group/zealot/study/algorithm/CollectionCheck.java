package group.zealot.study.algorithm;

import ch.qos.logback.classic.Level;
import group.zealot.study.algorithm.collection.OnlyOne;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static group.zealot.study.algorithm.util.Utils.NumbersUtil;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class CollectionCheck {
    @Autowired
    ApplicationContext context;

    @Before
    public void setLevel() {
//        Run.setLogLevel(Level.INFO);// checkCareful()打开
        Run.setLogLevel(Level.TRACE);
    }

    @Test
    public void check() {
        int[] numbers = NumbersUtil.create(100, 10);
        int i = context.getBean(OnlyOne.class).run(numbers);
        System.out.println(i);
    }
}
