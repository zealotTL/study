package group.zealot.study.datasource;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.datasource.jpa.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
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
import java.util.List;

/**
 * @author zealot
 * @date 2019/11/16 12:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
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

    @Autowired
    private JpaRepository jpaRepository;

    @Test
    public void studyJdbc() {
        int i = jdbcTemplate.queryForObject("select 1", int.class);
        logger.info("i: " + i);
    }

    @Test
    public void studyRedis() {
        String name = redisTemplate.opsForValue().get("name");
        logger.info("sftpname: " + name);
    }

    @Test
    public void studyActimvemq() {
        jmsTemplate.convertAndSend("test", "this test message");
    }

    @JmsListener(destination = "test", containerFactory = "activemqJmsListenerFactory")
    public void recover(Session session, Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        logger.info("jms: " + textMessage.getText());
    }

    @Test
    public void studyJpa() {
        {
            User item = new User();
            item.setName(random());
            item.setEmail(item.getName() + "@qq.com");
            jpaRepository.save(item);
        }

        {
            List<User> list = jpaRepository.findAll();
            list.forEach(user -> logger.info(JSONObject.toJSONString(user)));

        }
        {
            User vo = new User();
            vo.setName("a");

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                    .withIgnorePaths("email");//忽略字段，即不管password是什么值都不加入查询条件

            List<User> list = jpaRepository.findAll(Example.of(vo, matcher));
            list.forEach(user -> {
                logger.info(JSONObject.toJSONString(user));
                user.setIsDelete(!user.getIsDelete());
            });
            jpaRepository.saveAll(list);
        }

    }

    private String random() {
        String str = "qazwsxedcrfvtgbyhnujmikolp";
        int length = (int) (Math.random() * 10);
        StringBuilder name = new StringBuilder();
        while (length-- > 0) {
            int start = (int) (Math.random() * 1000) % 27;
            name.append(str.charAt(start));
        }
        return name.toString();
    }

}
