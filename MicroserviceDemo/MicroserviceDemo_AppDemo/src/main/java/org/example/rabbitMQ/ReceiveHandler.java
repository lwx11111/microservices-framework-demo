package org.example.rabbitMQ;

import org.example.domain.ShopItem;
import org.example.service.IShopItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2023/7/25 23:45
 */
@Configuration
public class ReceiveHandler {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IShopItemService service;


    @RabbitListener(queues = {RabbitmqConfig.ORDER_PEDDING_TOPIC})
    public void receive_order(ShopItem obj){
        System.out.println("消息队列添加");
        service.saveByParam(obj,obj.getParams());
    }
}
