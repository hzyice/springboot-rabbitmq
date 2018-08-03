package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 通配符模式
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootRabbitmqApplication.class)
public class TopicSenderTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void testSend() {
        // *(一个单词) 与 #（多个单词）区别: “#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，
        // 无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。
        // 当匹配不成功时，junit也会显示成功。只是消息没被消费，不代表代码有误

        // # 模式
        topicSender.send();

        // * 模式  （测试时，要把 RabbitConfig 类中的 * 模式注解打开）
//        topicSender.sendTwo();
    }
}
