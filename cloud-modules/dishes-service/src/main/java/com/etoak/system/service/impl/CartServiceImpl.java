package com.etoak.system.service.impl;

import com.etoak.common.contant.CommonConstant;
import com.etoak.common.redis.RedisService;
import com.etoak.common.web.context.LoginUserContext;
import com.etoak.system.entity.Cart;
import com.etoak.system.entity.Dishes;
import com.etoak.system.service.CartService;
import com.etoak.system.service.DishesService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    RedisService redisService;

    @Autowired
    DishesService dishesService;

    @Override
    public void saveOrUpdate(Cart cart) {
        redisService.hset(getCartKey(), cart.getDishesId().toString(), cart.getCount().toString());
    }

    @Override
    public List<Cart> list() {
        List<Cart> cartList = new ArrayList<>();

        Map<String, String> cartMap = redisService.hgetAll(getCartKey());
        if(MapUtils.isNotEmpty(cartMap)){
            //获取cartMap的所有的key(菜品id)
            List<Integer> dishesIds = cartMap.keySet().stream().map(Integer::parseInt).toList();

            //所有的菜品信息
            List<Dishes> dishesList = dishesService.listByIds(dishesIds);

            cartList = dishesList.stream().map(dishes ->  {
                Cart cart = new Cart();
                cart.setDishesId(dishes.getId());
                cart.setDishesName(dishes.getName());

                String count = cartMap.get(String.valueOf(cart.getDishesId()));
                cart.setCount(Integer.valueOf(count));
                return cart;
            }).toList();
        }

        return cartList;
    }

    @Override
    public void delete(List<String> ids) {
        redisService.hdel(getCartKey(), ids.toArray(new String[0]));
    }

    private String getCartKey(){
        return CommonConstant.CART_KEY_PREFIX + LoginUserContext.getId();
    }
}
