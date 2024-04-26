package org.example.rabbitMQ;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2023/7/25 23:42
 */
@Configuration
public class RabbitmqConfig {

    //订单队列
    public static final String ORDER_PEDDING_TOPIC = "ORDER_PEDDING_TOPIC";

    //声明ORDER_PEDDING_TOPIC队列
    @Bean(ORDER_PEDDING_TOPIC)
    public Queue ORDER_PEDDING_TOPIC(){
        return new Queue(ORDER_PEDDING_TOPIC);
    }

}
