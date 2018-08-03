package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import com.hzyice.springbootrabbitmq.pojo.UserPojo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 实体类传输
@Component
public class PojoSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        UserPojo userPojo = new UserPojo();
        userPojo.setAge(18);
        userPojo.setUserName("张三");
        rabbitTemplate.convertAndSend(RabbitConstants.POJO_QUEUE, userPojo);
    }



}
