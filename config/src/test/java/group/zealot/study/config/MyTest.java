package group.zealot.study.config;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = MyTest.class)
@SpringBootApplication(scanBasePackages = "group.zealot.study")
public class MyTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ApplicationContext context;

    @NacosValue("${nacos.test}")
    private String test;

    @Test
    public void studyJdbc() {
        logger.info(context.getEnvironment().getProperty("nacos.test"));
        logger.info(test);
    }

    public void setTest(String test) {
        this.test = test;
    }
}
