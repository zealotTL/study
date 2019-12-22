package group.zealot.study.dubbo.bService.test;

import group.zealot.study.dubbo.base.NameService;
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
public class TestNameService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private NameService nameService;

    @Test
    public void doNameService() {
        logger.info("sftpname: " + nameService.name());
    }
}
