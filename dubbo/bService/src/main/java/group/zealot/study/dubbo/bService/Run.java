package group.zealot.study.dubbo.bService;


import group.zealot.study.dubbo.base.NameService;
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
    private NameService nameService;

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        String name = context.getBean(Run.class).nameService.name();
        logger.info("name: " + name);
    }
}
