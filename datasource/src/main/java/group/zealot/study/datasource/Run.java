package group.zealot.study.datasource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        Run run = context.getBean(Run.class);

        int i = run.jdbcTemplate.queryForObject("select 1", int.class);
        System.out.println(i);
    }
}
