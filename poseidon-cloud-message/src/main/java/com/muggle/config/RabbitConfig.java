package com.muggle.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class RabbitConfig {
    /**
     * 只有队列的情况(Direct exchange 直连交换机 这里用了Default Exchange 不需要new)
     * @return
     */
    @Bean
    public Queue getSimpleQueue(){
        return new Queue("simple-queue");
    }

    @Bean
    public Queue getObjSimpleQueue(){
        return new Queue("obj-simple-queue");
    }

    /**
     * Fanout exchange（扇型交换机） 不识别路由键
     * @return
     */
    @Bean("fanoutQueueFirst")
    public Queue fanoutQueueFirst(){
        return new Queue("first-fanout-queue");
    }

    @Bean("fanoutQueueSecond")
    public Queue fanoutQueueSecond(){
        return new Queue("second-fanout-queue");
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Binding bingQueueFirstToExchange(@Qualifier("fanoutQueueFirst")Queue queue,@Qualifier("fanoutExchange")FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bingQueueSecondToExchange(@Qualifier("fanoutQueueSecond")Queue queue,@Qualifier("fanoutExchange")FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * direct  直连交换机 依靠路由键匹配
     * @return
     */
    @Bean("directExchange")
    public DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }

    @Bean("directQueueFirst")
    public Queue directQueueFirst(){
        return  new Queue("first-direct-queue");
    }

    @Bean("directQueueSecond")
    public Queue directQueueSecond(){
        return  new Queue("second-direct-queue");
    }

    @Bean
    public Binding bingQueueFirstToDirect(@Qualifier("directQueueFirst")Queue queue,@Qualifier("directExchange")DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("first-key");
    }

    @Bean
    public Binding bingQueueSecondToDirect(@Qualifier("directQueueSecond")Queue queue,@Qualifier("directExchange")DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("second-key");
    }

    /**
     * 主题交换机  Topic  路由键模糊匹配  以.分割单词 ，*匹配一个单词，#匹配多个单词 如路由键是com.muggle.first 能被com.#绑定键匹配
     * @return
     */
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }

    @Bean("topicQueueFirst")
    public Queue topicQueueFirst(){
        return new Queue("first-topic-queue");
    }

    @Bean("topicQueueSecond")
    public Queue topicQueueSecond(){
        return new Queue("second-topic-queue");
    }

    @Bean
    public Binding bindTopicFirst(@Qualifier("topicQueueFirst")Queue queue, @Qualifier("topicExchange")TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("com.muggle.first");
    }

    @Bean
    public Binding bindTopicSecond(@Qualifier("topicQueueFirst")Queue queue, @Qualifier("topicExchange")TopicExchange exchange){
        return BindingBuilder.bind(topicQueueFirst()).to(topicExchange()).with("com.#");
    }


}
