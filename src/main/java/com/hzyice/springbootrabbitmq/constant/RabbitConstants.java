package com.hzyice.springbootrabbitmq.constant;

/**
 * rabbit常量
 */
public class RabbitConstants {

    public static final String DIRECT_EXCHANGE = "test.exchange.direct";

    public static final String FANOUT_EXCHANGE = "test.exchange.fanout";

    public static final String TOPIC_EXCHANGE = "test.exchange.topic";

    public static final String TOPIC_QUEUE = "test.topic.queue";

    public static final String DIRECT_QUEUE = "test.direct.queue";

    public static final String FANOUT_QUEUE_ONE = "test.fanout.queue.one";

    public static final String FANOUT_QUEUE_TWO = "test.fanout.queue.two";

    public static final String TWORK_QUEUE = "test.work.queue";

    public static final String POJO_QUEUE = "test.pojo.queue";

    public static final String CALLBACK_QUEUE = "test.callback.queue";

    // 以下消息确认机制的常量
    public static final String EXCHANGE = "exchangeTest";

    public static final String ROUTINGKEY1 = "queue_one_key1";
}
