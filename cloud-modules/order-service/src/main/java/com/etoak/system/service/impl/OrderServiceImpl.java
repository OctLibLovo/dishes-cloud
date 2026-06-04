package com.etoak.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.enums.OrderStateEnum;
import com.etoak.common.vo.ResultVO;
import com.etoak.common.web.context.LoginUserContext;
import com.etoak.config.OrderQueueConfig;
import com.etoak.system.api.DishesService;
import com.etoak.system.dto.OrderDTO;
import com.etoak.system.entity.Order;
import com.etoak.system.entity.OrderItem;
import com.etoak.system.mapper.OrderMapper;
import com.etoak.system.service.OrderItemService;
import com.etoak.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.system.vo.DishesVO;
import com.etoak.system.vo.OrderVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    DishesService dishesService;

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

    @Override
    public List<OrderVO> getDetail(String orderNo) {
        List<OrderVO> orderVOList = new ArrayList<>();

        List<OrderItem> orderItemsList = orderItemService.lambdaQuery().eq(OrderItem::getOrderNo, orderNo).list();

        Map<Integer, Integer> dishesIdMap = new HashMap<>();

        if(CollectionUtils.isNotEmpty(orderItemsList)){
            // 获取所有的菜品id
            List<Integer> dishesIds = orderItemsList.stream().map(OrderItem::getDishesId).toList();

            orderItemsList.forEach(item -> dishesIdMap.put(item.getDishesId(), item.getDishesNum()));

            ResultVO<List<DishesVO>> resultVO = dishesService.listByIds(dishesIds);
            if (resultVO.getCode().equals(ResultVO.SUCCESS_CODE) && CollectionUtils.isNotEmpty(resultVO.getData())) {
                List<DishesVO> dishesVOList = resultVO.getData();
                orderVOList = dishesVOList.stream().map(dishesVO -> {
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderNo(orderNo);
                    orderVO.setDishesName(dishesVO.getName());
                    orderVO.setDishesNum(dishesIdMap.get(dishesVO.getId()));

                    String detail = StringUtils.join(dishesVO.getMainName(), ":", dishesVO.getMainNum(), "g; ",
                            dishesVO.getMinorName(), " : ", dishesVO.getMinorNum(), "g; ",
                            dishesVO.getSeasoningName(), " : ", dishesVO.getSeasoningNum(), "g;");
                    orderVO.setDetail(detail);
                    return orderVO;
                }).toList();
            }
        }
        return orderVOList;
    }
}
