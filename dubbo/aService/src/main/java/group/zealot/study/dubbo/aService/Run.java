package group.zealot.study.dubbo.aService;


import group.zealot.study.dubbo.base.AgeService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    @Reference
    private AgeService ageService;

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        String age = context.getBean(Run.class).ageService.age();
        logger.info("age: " + age);
    }
}
