package com.hzyice.springbootrabbitmq.receiver;


import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import com.hzyice.springbootrabbitmq.pojo.UserPojo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConstants.POJO_QUEUE)
public class PojoReceiver {


    @RabbitHandler
    public void process(UserPojo userPojo) {
        System.out.println("UserPojo= " + userPojo.toString());
    }



}
