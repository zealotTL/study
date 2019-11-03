package group.zealot.study.dubbo.bService;


import group.zealot.study.dubbo.base.NameService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    @Reference
    private NameService nameService;

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        String name = context.getBean(Run.class).nameService.name();
        System.out.println("name: " + name);
    }
}
