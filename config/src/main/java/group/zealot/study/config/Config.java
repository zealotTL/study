package group.zealot.study.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NacosPropertySource(dataId = "config-nacos", autoRefreshed = true, type = ConfigType.YAML)
public class Config {

    @NacosValue(value = "${test.name}", autoRefreshed = true)
    public String name;
}
