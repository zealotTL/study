package group.zealot.study.datasource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class DataSourceConfig {
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
}
