package com.hzyice.springbootrabbitmq.configuration;

import com.hzyice.springbootrabbitmq.callback.AckConfirm;
import com.hzyice.springbootrabbitmq.callback.AckReturn;
import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import com.hzyice.springbootrabbitmq.receiver.AckReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq配置
 */
@Configuration
public class RabbitConfig {

    /************************************** 消息确认机制配置开始 **************************************/
    @Value("${spring.rabbitmq.host}")
    public String host;
    @Value("${spring.rabbitmq.port}")
    public int port;
    @Value("${spring.rabbitmq.username}")
    public String username;
    @Value("${spring.rabbitmq.password}")
    public String password;
    @Value("${spring.rabbitmq.virtual-host}")
    public String virtualHost;



    //创建连接工厂
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host, this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    // 1.定义direct exchange
    // 2.durable="true" rabbitmq重启的时候不需要创建新的交换机
    @Bean
    public DirectExchange directExchangeCallback(){
        DirectExchange directExchange = new DirectExchange(RabbitConstants.EXCHANGE,true,false);
        return directExchange;
    }

    // 定义queue_one
    @Bean
    public Queue queue_one(){
        /**
         *   durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         exclusive  表示该消息队列是否只在当前connection生效,默认是false*/
        Queue queue = new Queue("queue_one",true,false,false);
        return queue;
    }


    //将消息队列1和交换机进行绑定
    @Bean
    public Binding binding_one() {
        return BindingBuilder.bind(queue_one()).to(directExchangeCallback()).with(RabbitConstants.ROUTINGKEY1);
    }



    // queue litener  观察 监听模式 当有消息到达时会通知监听在对应的队列上的监听对象
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer_one(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        simpleMessageListenerContainer.addQueues(queue_one());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        simpleMessageListenerContainer.setMessageListener(ackReceiver());
        return simpleMessageListenerContainer;
    }

    // 定义消费者
    @Bean
    public AckReceiver ackReceiver(){
        return new AckReceiver();
    }


    // 定义rabbit template用于数据的接收和发送
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        /**若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback*/

        template.setConfirmCallback(ackConfirm());
        template.setReturnCallback(ackReturn());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥*/
       template.setMandatory(true);
        return template;
    }

    /**
     消息确认机制
     Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)在channel为事务时，
     不可引入确认模式；同样channel为确认模式下，不可使用事务。
     */
    @Bean
    public AckConfirm ackConfirm(){
        return new AckConfirm();
    }

    @Bean
    public AckReturn ackReturn(){
        return new AckReturn();
    }

    /************************************** 消息确认机制配置结束 **************************************/

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstants.TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstants.FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConstants.DIRECT_EXCHANGE);
    }


    @Bean
    public Queue callbackQueue() {
        return new Queue(RabbitConstants.CALLBACK_QUEUE);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(RabbitConstants.TOPIC_QUEUE);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(RabbitConstants.DIRECT_QUEUE);
    }

    @Bean
    public Queue fanoutQueueOne() {
        return new Queue(RabbitConstants.FANOUT_QUEUE_ONE);
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue(RabbitConstants.FANOUT_QUEUE_TWO);
    }


    @Bean
    public Queue workQueue() {
        return new Queue(RabbitConstants.TWORK_QUEUE);
    }

    // 如果rabbitMQ 程序宕机了， 会把消息持久化，等再次开机时队列的消息还存在。
    // durable: 耐用的
//    @Bean
//    public Queue workQueue() {
//        return new Queue(RabbitConstants.TWORK_QUEUE,true);
//    }

    @Bean
    public Queue pojoQueue() {
        return new Queue(RabbitConstants.POJO_QUEUE);
    }



    /**
     * 多个单词匹配
     * 绑定topicExchange和topicQueue对应的routing-key为test.topic.#
     */
    @Bean
    public Binding topicBinder() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("test.topic.#");
    }
    //  * 一个单词匹配
//    @Bean
//    public Binding topicBinderTwo() {
//        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("test.to.*");
//    }

    /**
     * 绑定directExchange和directQueue并指定routing-key为test.direct
     * 往directExchange发送消息时，只有消息头的routing-key为test.direct的消息才会进入到directQueue队列中
     */
    // directExchange: 路由模式
    @Bean
    public Binding directBinder() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("test.direct");
    }

    /**
     * 绑定fanoutExchange和fanoutQueueOne
     *
     * 只要往fanoutExchange发送消息，消息就会进入fanoutQueueOne队列
     * @return
     */
    @Bean
    public Binding fanoutBinderOne() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    /**
     * 绑定fanoutExchange和fanoutQueueTow
     * 只要往fanoutExchange发送消息，消息就会进入fanoutQueueTwo队列
     * @return
     */
    @Bean
    public Binding fantouBinderTwo() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }


}
