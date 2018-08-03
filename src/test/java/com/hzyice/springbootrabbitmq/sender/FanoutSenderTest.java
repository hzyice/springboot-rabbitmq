package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//订阅（广播）模式
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class FanoutSenderTest {

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void testSend() {
        fanoutSender.send();
    }
}
