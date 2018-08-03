package com.hzyice.springbootrabbitmq.sender;


import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// work 模式: 一个交换机被多个队列绑定，当生产者发送消息后，队列默认采用轮询方式消费

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class WorkSenderTest {

    @Autowired
    private WorkSender workSender;


    @Test
    public void send() throws Exception {
        workSender.send();
    }

}