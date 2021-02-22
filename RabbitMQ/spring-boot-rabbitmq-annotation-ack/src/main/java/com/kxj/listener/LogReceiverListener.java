package com.kxj.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xiangjin.kong
 * @date 2021/2/22 14:36
 */
@Slf4j
@Component
public class LogReceiverListener {

    /**
     * 接收info级别的日志
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${log.info.queue}", durable = "true"),
            exchange = @Exchange(value = "${log.exchange}", type = ExchangeTypes.TOPIC),
            key = "${log.info.binding-key}"))
    public void infoLog(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("infoLogQueue 收到的消息为：{}", msg);
        try {
            // 各种业务逻辑编写
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    /**
     * 接收所有的日志
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${log.all.queue}", durable = "true"),
            exchange = @Exchange(value = "${log.exchange}", type = ExchangeTypes.TOPIC),
            key = "${log.all.binding-key}"))
    public void allLog(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("allLogQueue 收到的消息为:{}", msg);
        try {
            // 各种业务逻辑编写
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
