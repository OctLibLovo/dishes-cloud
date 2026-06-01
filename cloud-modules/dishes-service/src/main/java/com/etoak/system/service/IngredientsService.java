package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.system.entity.Ingredients;

public interface IngredientsService extends IService<Ingredients> {
    /**
     * 添加食材
     *
     * @param ingredients 食材参数
     */
    void add(Ingredients ingredients);
}
