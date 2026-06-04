package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.dto.OrderDTO;
import com.etoak.system.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author etoak
 * @since 2026-06-04
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 创建订单 post /order
     */
    @PostMapping
    public ResultVO<Object> create(@Valid @RequestBody OrderDTO orderDTO){
        orderService.create(orderDTO);
        return ResultVO.success();
    }

}

