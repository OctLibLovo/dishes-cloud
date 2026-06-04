package com.etoak.system.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.common.enums.OrderStateEnum;
import com.etoak.system.entity.Order;
import com.etoak.system.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class CancelService {
    @Autowired
    OrderMapper orderMapper;

    @RabbitListener(queues = "order")
    public void Cancel(Channel channel, Message message){

        try {
            String orderNo = new String(message.getBody());
            log.info("orderNo:{}" + orderNo);

            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_no", orderNo);
            Order order = orderMapper.selectOne(queryWrapper);

            /* 查到订单并且订单状态为新建/未支付 */
            if (ObjectUtils.isNotEmpty(order) && order.getOrderState().equals(OrderStateEnum.NEW.getValue())) {

                order.setOrderState(OrderStateEnum.CANCEL.getValue());
                order.setCancelTime(DateUtil.now());
                order.setCancelReason("超时未支付，自动取消");

                orderMapper.updateById(order);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException ex) {
                log.error(e.getMessage(), e);
            }
        }

    }
}
