package group.zealot.study.dubbo.bService;

import group.zealot.study.dubbo.base.AgeService;
import group.zealot.study.dubbo.base.BaseService;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceClass = AgeService.class)
public class AgeServiceImpl extends BaseService implements AgeService {
    @Override
    public String age() {
        logger.info("调用 age() ");
        return "23 age";
    }
}
