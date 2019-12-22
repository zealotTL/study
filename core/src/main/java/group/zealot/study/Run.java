package group.zealot.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"group.zealot.study.config", "group.zealot.study.core"})
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}
