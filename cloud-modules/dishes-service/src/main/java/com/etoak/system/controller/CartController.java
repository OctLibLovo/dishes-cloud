package com.etoak.system.controller;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Cart;
import com.etoak.system.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * 添加修改购物车
     * @param cart 添加修改购物车参数
     * @return ResultVO
     */
    @PostMapping
    public ResultVO<Object> saveOrUpdate(@Valid @RequestBody Cart cart){
        cartService.saveOrUpdate(cart);
        return ResultVO.success();
    }

    /**
     * 查询购物车菜品列表
     */
    @GetMapping("/list")
    public ResultVO<List<Cart>> list(){
        return ResultVO.success(cartService.list());
    }

    /**
     * 刹车农户 菜品
     * @param ids 要删除的菜品的id
     * @return ResultVO
     */
    @PostMapping("/{ids}")
    public ResultVO<Object> delete(@PathVariable List<String> ids){
        cartService.delete(ids);
        return ResultVO.success();
    }
}
