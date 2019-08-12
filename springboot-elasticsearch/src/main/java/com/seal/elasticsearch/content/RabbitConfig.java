package com.seal.elasticsearch.content;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/25 19:05
 * @description TODO
 **/
@Configuration
public class RabbitConfig {


    public static final String QUEUE_NAME = "re.regionQueue";

    public static final String TOPIC_EXCHANGE_NAME = "reExchange";

    public static final String UE_ROUTE_KEY = "re.region";

    @Bean(name = "message")
    public Queue queueMessage() {
        return new Queue(QUEUE_NAME);
    }

    @Bean(name = "exchange")
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,
                                   @Qualifier("exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(UE_ROUTE_KEY);
    }


}
