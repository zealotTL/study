package group.zealot.study.dubbo.aService;

import group.zealot.study.dubbo.base.BaseService;
import group.zealot.study.dubbo.base.NameService;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceClass = NameService.class)
public class NameServiceImpl extends BaseService implements NameService {
    @Override
    public String name() {
        logger.info("调用 name() ");
        return "a service";
    }
}
