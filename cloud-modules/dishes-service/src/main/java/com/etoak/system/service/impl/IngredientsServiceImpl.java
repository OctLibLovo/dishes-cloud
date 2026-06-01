package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.common.exception.ParamException;
import com.etoak.system.entity.Ingredients;
import com.etoak.system.mapper.IngredientsMapper;
import com.etoak.system.service.IngredientsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class IngredientsServiceImpl extends ServiceImpl<IngredientsMapper, Ingredients> implements IngredientsService {
    @Override
    public void add(Ingredients ingredients) {
        if(ObjectUtils.isNotEmpty(getByName(ingredients.getName()))){
            throw new ParamException("食材名称重复!");
        }
        this.save(ingredients);
    }

    private Ingredients getByName(String name){
        return lambdaQuery().eq(Ingredients::getName, name).one();
    }
}
