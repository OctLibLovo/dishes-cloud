package com.etoak.system.service;

import com.etoak.system.dto.OrderDTO;
import com.etoak.system.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.system.vo.OrderVO;
import jakarta.validation.Valid;

import java.util.List;

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

    /**
     * 获取订单详情列表
     * @param orderNo 订单编号
     * @return 订单详情列表i
     */
    List<OrderVO> getDetail(String orderNo);

}
