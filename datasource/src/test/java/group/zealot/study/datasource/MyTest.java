package group.zealot.study.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {Run.class})
public class MyTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void studyJdbc() {
        int i = jdbcTemplate.queryForObject("select 1", int.class);
        logger.info("i: " + i);
    }

    @Test
    public void studyRedis() {
        String name = redisTemplate.opsForValue().get("name");
        logger.info("name: " + name);
    }

    @Test
    public void studyActimvemq() {
        jmsTemplate.convertAndSend("test", "this test message");
    }

    @Test
    @JmsListener(destination = "test", containerFactory = "defaultJmsListenerContainerFactory")
    protected void test(Session session, Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        logger.info("jms: " + textMessage.getText());
    }
}
