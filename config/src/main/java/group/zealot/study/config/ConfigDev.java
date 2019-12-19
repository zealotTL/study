package group.zealot.study.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
@NacosPropertySource(dataId = "config-datasource-dev", autoRefreshed = true, type = ConfigType.YAML)
public class ConfigDev {
}
