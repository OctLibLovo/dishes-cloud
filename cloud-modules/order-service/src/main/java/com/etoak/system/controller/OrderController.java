package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.dto.OrderDTO;
import com.etoak.system.service.OrderService;
import com.etoak.system.vo.DishesVO;
import com.etoak.system.vo.OrderVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/detail")
    public ResultVO<List<OrderVO>> getDetail(@RequestParam String orderNo){
        List<OrderVO> detail = orderService.getDetail(orderNo);
        return ResultVO.success(detail);
    }
}

