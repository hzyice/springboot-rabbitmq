package com.hzyice.springbootrabbitmq.receiver;


import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConstants.TWORK_QUEUE)
public class WorkReceiverTwo {


    @RabbitHandler
    public void process2(String s) {
        System.out.println("2 work 消息执行了...=" + s);
    }
}
