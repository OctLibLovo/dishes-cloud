package com.etoak.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.enums.OrderStateEnum;
import com.etoak.common.web.context.LoginUserContext;
import com.etoak.config.OrderQueueConfig;
import com.etoak.system.dto.OrderDTO;
import com.etoak.system.entity.Order;
import com.etoak.system.entity.OrderItem;
import com.etoak.system.mapper.OrderMapper;
import com.etoak.system.service.OrderItemService;
import com.etoak.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author etoak
 * @since 2026-06-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(OrderDTO orderDTO) {
        // 雪花算法实现分布式ID
        String orderNo = IdUtil.getSnowflakeNextIdStr();

        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(Integer.parseInt(LoginUserContext.getId()));
        order.setPrepareTime(orderDTO.getPrepareTime());

        order.setOrderState(OrderStateEnum.NEW.getValue());
        order.setCreateTime(DateUtil.now());

        this.save(order);

        /* 批量保存订单项 */
        orderDTO.getItemList().forEach(item -> item.setOrderNo(orderNo));
        orderItemService.saveBatch(orderDTO.getItemList());

        rabbitTemplate.convertAndSend(OrderQueueConfig.EXCHANGE,
            OrderQueueConfig.KEY,
            orderNo,
            message -> {
                message.getMessageProperties().setDelayLong(10 * 1000L);
                return message;
            });

    }
}
