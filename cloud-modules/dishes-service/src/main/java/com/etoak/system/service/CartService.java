package com.etoak.system.service;

import com.etoak.system.entity.Cart;

import java.util.List;

public interface CartService {
    /**
     * 添加修改购物车
     * @param cart
     */
    void saveOrUpdate(Cart cart);

    /**
     * 获取当前用户的购物车列表
     * @return
     */
    List<Cart> list();

    /**
     * 删除购物车中的物品
     * @param ids
     */
    void delete(List<String> ids);
}
