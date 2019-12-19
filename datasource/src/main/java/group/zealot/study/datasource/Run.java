package group.zealot.study.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "group.zealot")
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);
    }

}
