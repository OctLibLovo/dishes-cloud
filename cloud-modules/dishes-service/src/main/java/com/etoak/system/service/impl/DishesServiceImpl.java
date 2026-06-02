
package com.etoak.system.service.impl;


import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Dishes;
import com.etoak.system.mapper.DishesMapper;
import com.etoak.system.service.DishesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.system.vo.DishesVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * DishesServiceImpl
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements DishesService {

    @Override
    public void add(Dishes dishes) {
        if (ObjectUtils.isNotEmpty(this.getById(dishes.getName()))) {
            throw new ParamException("菜品名称重复");
        }
        this.save(dishes);
    }

    @Override
    public List<DishesVO> getList(Dishes dishes) {
        return baseMapper.getList(dishes);
    }

    @Override
    public PageVO<DishesVO> pageList(int pageNum, int pageSize, Dishes dishes) {
        PageHelper.startPage(pageNum, pageSize);
        List<DishesVO> dishesVOListh = this.getList(dishes);
        PageInfo<DishesVO> pageInfo = new PageInfo<>(dishesVOListh);
        return new PageVO<>(new PageInfo<>(dishesVOListh).getTotal(), dishesVOListh);
    }

    @Override
    public void update(Integer id, Dishes dishes) {
        if(ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("菜品不存在");
        }
        String dishesName = dishes.getName();
        if(StringUtils.isNotEmpty(dishesName)){
            Dishes savedDishes = this.getByName(dishesName);
            if(ObjectUtils.isNotEmpty(savedDishes) && !savedDishes.getId().equals(id)) {
                throw new ParamException("不能修改为其他菜品名称");
            }
        }
        dishes.setId(id);
        this.updateById(dishes);
    }

    @Override
    public void delete(int id) {
        if(ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("菜品不存在");
        }
        this.removeById(id);
    }

    private Dishes getByName(String dishesName){
        return lambdaQuery().eq(Dishes::getName, dishesName).one();
    }
}
