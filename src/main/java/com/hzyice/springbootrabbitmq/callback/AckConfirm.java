package com.hzyice.springbootrabbitmq.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;


public class AckConfirm implements RabbitTemplate.ConfirmCallback{
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // correlationData: 相当于标记，确认是用于哪一次发送。如果send()方法中不加这个参数,默认为Null
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            // cause: 失败的原因
            System.out.println("消息消费失败:" + cause+"\n重新发送");

        }
    }
}
