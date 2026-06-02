package com.etoak.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.system.entity.Dict;
import com.etoak.system.entity.Dishes;
import com.etoak.system.vo.DishesVO;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 查询表菜品列表
     * @param dishes 菜品查询参数
     * @return
     */

}
