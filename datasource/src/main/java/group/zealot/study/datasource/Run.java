package group.zealot.study.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        Run run = context.getBean(Run.class);
        run.studyJdbc();
        run.studyRedis();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void studyJdbc() {
        int i = jdbcTemplate.queryForObject("select 1", int.class);
        logger.info("i: " + i);
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private void studyRedis() {
        String name = redisTemplate.opsForValue().get("name");
        logger.info("name: " + name);
    }
}
