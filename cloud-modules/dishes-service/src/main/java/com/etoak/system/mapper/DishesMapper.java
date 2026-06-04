package com.etoak.system.mapper;

import com.etoak.system.entity.Dishes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.system.vo.DishesVO;

import java.util.List;

/**
 * <p>
 * DishesMapper 接口
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
public interface DishesMapper extends BaseMapper<Dishes> {

    /**
     * 查询表菜品列表
     * @param dishes 菜品查询参数
     * @return
     */
    List<DishesVO> getList(Dishes dishes);

    List<DishesVO> listByIds(List<Integer> ids);
}
