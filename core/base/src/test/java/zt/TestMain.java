package zt;

import com.alibaba.fastjson.JSONObject;
import group.zealot.study.Main;
import group.zealot.study.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = {Main.class})
public class TestMain {
    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    Environment environment;

    @Test
    public void task() {
        System.out.println(getCustSftpInfo().toJSONString());
    }

    protected JSONObject getCustSftpInfo() {
        JSONObject defaultDcp = getDefaultDefine("dcp");
        JSONObject defaultCmp = getDefaultDefine("cmp");

        JSONObject info = new JSONObject();
        JSONObject cust;
        for (String custType : new String[]{"dcp", "cmp"}) {
            int i = 0;
            JSONObject type;
            while (!StringUtil.isEmpty(environment.getProperty("cdr.push.cust." + custType + "[" + i + "].id"))) {
                cust = new JSONObject();
                cust.put("id", environment.getProperty("cdr.push.cust." + custType + "[" + i + "].id"));
                cust.put("sftp", environment.getProperty("cdr.push.cust." + custType + "[" + i + "].sftp"));
                if (StringUtil.isEmpty(environment.getProperty("cdr.push.cust." + custType + "[" + i + "].type[0].name"))) {
                    cust.put("type", custType.equals("cmp") ? defaultCmp : defaultDcp);
                } else {
                    int j = 0;
                    type = new JSONObject();
                    while (!StringUtil.isEmpty(environment.getProperty("cdr.push.cust." + custType + "[" + i + "].type[" + j + "].name"))) {

                        String name = environment.getProperty("cdr.push.cust." + custType + "[" + i + "].type[" + j + "].name");
                        String dir = environment.getProperty("cdr.push.cust." + custType + "[" + i + "].type[" + j + "].base-dir");
                        type.put(name, dir);
                        j++;
                    }
                    cust.put("type", type);
                }
                info.put(cust.getString("id"), cust);
                i++;
            }
        }
        return info;
    }

    private JSONObject getDefaultDefine(String type) {
        JSONObject defaultDefine = new JSONObject();
        int i = 0;
        JSONObject itemType;
        while (!StringUtil.isEmpty(environment.getProperty("cdr.push." + type + ".type[" + i + "].name"))) {
            itemType = new JSONObject();
            itemType.put("name", environment.getProperty("cdr.push." + type + ".type[" + i + "].name"));
            itemType.put("base-dir", environment.getProperty("cdr.push." + type + ".type[" + i + "].base-dir"));
            defaultDefine.put("type", itemType);
            i++;
        }
        return defaultDefine;
    }
}