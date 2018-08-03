package com.hzyice.springbootrabbitmq.receiver;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由模式消息接收
 * 监听队列 test.direct.queue
 */
@Component
@RabbitListener(queues = RabbitConstants.DIRECT_QUEUE)
public class DirectReceiver {

    private static Integer value = 1;

    @RabbitHandler
    public void process(Object message) {
        System.out.println("路由模式执行了" + value++ +"...");
    }
}

