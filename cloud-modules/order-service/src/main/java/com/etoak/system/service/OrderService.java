package com.etoak.system.service;

import com.etoak.system.dto.OrderDTO;
import com.etoak.system.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author etoak
 * @since 2026-06-04
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @param orderDTO
     */
    void create(OrderDTO orderDTO);
}
