package group.zealot.study.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@NacosPropertySource(dataId = "config-datasource", autoRefreshed = true, type = ConfigType.YAML)
public class Config {
}