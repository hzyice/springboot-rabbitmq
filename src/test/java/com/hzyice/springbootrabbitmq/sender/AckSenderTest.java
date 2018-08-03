package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 消息确认机制

/*
    关于消息接收后，处理过程中宕机的处理思路：
        1）在send前，添加一个correlationDataId（后续简称ID）入参，作为消息的唯一标识。把要发送的message和ID存入
            缓存中（建议redis），并设置超时时间。
        2）当confirm或return回调时，根据ack分类处理。例 若ack=false 或 执行了 return方法则说明代码有问题。触发报警
            （发邮件或短信等）。如果 ack=true 则把ID对应缓存删除。注意：若 ack的值为true了，但执行了return方
             法，也按触发报警处理。
        3）定时检查缓存列表。手动处理未return&&confirm数据且又超时的数据。
        4）如果业务逻辑需要自动重发消息，则在消费端做好幂等和去重处理。
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class AckSenderTest {

    @Autowired
    private AckSender ackSender;

    private String correlationDataId = "666";


    @Test
    public void test1() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        //exchange,routingkey 都正确,confirm被回调, ack=true
        ackSender.send(RabbitConstants.EXCHANGE, RabbitConstants.ROUTINGKEY1, message, correlationDataId);
    }

    @Test
    public void test2() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        //exchange 错误,routingkey 正确,confirm被回调, ack=false
        ackSender.send(RabbitConstants.EXCHANGE+"NO",RabbitConstants.ROUTINGKEY1,message, correlationDataId);
    }

    @Test
    public void test3() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        // exchange 交换机不具存储功能。传递的消息是存储在queue队列中。
        // 虽然生产者消息发送成功，但routingKey是错误的，所以消费者并没有真正的消费。
        // 此例会执行 AckReturn ,然后再执行 AckConfirm(ack为true)
        //exchange 正确,routingkey 错误 ,confirm被回调, return被回调,  ack=true
        ackSender.send(RabbitConstants.EXCHANGE,"",message, correlationDataId);
    }

    @Test
    public void test4() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        //exchange 错误,routingkey 错误,confirm被回调, ack=false
        ackSender.send(RabbitConstants.EXCHANGE+"NO",RabbitConstants.ROUTINGKEY1 + "error",message, correlationDataId);
    }

}