package group.zealot.study.demo.core.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@NacosPropertySource(dataId = "config-demo", autoRefreshed = true, type = ConfigType.YAML)
public class Config {

}
