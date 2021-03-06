package group.zealot.study.dubbo.aService.test;

import group.zealot.study.dubbo.base.AgeService;
import group.zealot.study.dubbo.base.Run;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zealot
 * @date 2019/11/23 13:26
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = {Run.class})
public class TestAgeService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private AgeService ageService;

    @Test
    public void doNameService() {
        logger.info("age: " + ageService.age());
    }
}
