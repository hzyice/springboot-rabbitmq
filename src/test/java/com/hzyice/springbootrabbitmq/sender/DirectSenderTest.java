package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 路由模式消息接收
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class DirectSenderTest {

    @Autowired
    private DirectSender directSender;

    @Autowired
    private FanoutSender fanoutSender;


    @Test
    public void testSend() {
        directSender.send();
        // 一个队列不能同时绑定多个exchange(交换机)。
//        fanoutSender.send();

    }
}
