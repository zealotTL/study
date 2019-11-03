package group.zealot.study.datasource;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@SpringBootApplication
public class Run {
    public static ConfigurableApplicationContext context;

    protected static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        context = SpringApplication.run(Run.class, args);

        Run run = context.getBean(Run.class);
        run.studyJdbc();
        run.studyRedis();
        run.studyActimvemq();
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


    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(JmsPoolConnectionFactory jmsPoolConnectionFactory) {
        ActiveMQConnectionFactory factory = (ActiveMQConnectionFactory) jmsPoolConnectionFactory.getConnectionFactory();
        RedeliveryPolicy redeliveryPolicy = factory.getRedeliveryPolicy();
        redeliveryPolicy.setUseExponentialBackOff(false);//重发时间间隔递增
        redeliveryPolicy.setBackOffMultiplier(2.0);//重发时间间隔倍数增长，需 UseExponentialBackOff 为true
        redeliveryPolicy.setMaximumRedeliveries(5);//最大重发次数
        redeliveryPolicy.setMaximumRedeliveryDelay(3000);//最大重发间隔时间
        redeliveryPolicy.setInitialRedeliveryDelay(1000);//初始重发时间间隔

        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(jmsPoolConnectionFactory);
        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);//客户端调用acknowledge方法手动签收  activemq专用4
        defaultJmsListenerContainerFactory.setPubSubDomain(false);
        return defaultJmsListenerContainerFactory;
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    private void studyActimvemq() {
        jmsTemplate.convertAndSend("test", "this test message");
    }


    @JmsListener(destination = "test",containerFactory = "defaultJmsListenerContainerFactory")
    protected void test(Session session, Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        logger.info("jms: " + textMessage.getText());
    }

}
