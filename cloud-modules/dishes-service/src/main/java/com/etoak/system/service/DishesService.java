package com.etoak.system.service;

import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Dishes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.system.vo.DishesVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * DishesService
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
public interface DishesService extends IService<Dishes> {

    /**
     * 添加菜品
     * @param dishes
     */
    void add(Dishes dishes);

    /**
     * 查询菜品李彪
     * @param dishes 菜品查询参数
     * @return List<DishesVO>
     */
    List<DishesVO> getList(Dishes dishes);

    PageVO<DishesVO> pageList(int pageNum, int pageSize, Dishes dishes);

    /**
     * 更新菜品
     * @param id 菜品id
     * @param dishes 菜品更新参数
     */
    void update(Integer id, Dishes dishes);

    /**
     * 删除菜品
     * @param id 删除参数
     */
    void delete(int id);

}
