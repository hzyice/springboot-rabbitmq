package com.hzyice.springbootrabbitmq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;


public class AckReceiver implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(AckReceiver.class);

    @Override
    public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
        try{
            System.out.println("收到消息1 : ");//确认消息成功消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch(Exception e){
            e.printStackTrace();//TODO 业务处理
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }

    }
}
