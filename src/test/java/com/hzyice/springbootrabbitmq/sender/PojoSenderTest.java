package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// rabbitmq  实体类信息传送 ， pojo类需要 implements 序列化接口

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class PojoSenderTest {


    @Autowired
    private PojoSender pojoSender;


    @Test
    public void send() throws Exception {
        pojoSender.send();
    }

}