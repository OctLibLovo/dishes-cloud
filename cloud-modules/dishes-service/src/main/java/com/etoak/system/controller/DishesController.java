package com.etoak.system.controller;

import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Dishes;
import com.etoak.system.service.DishesService;
import com.etoak.system.vo.DishesVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * DishesController
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
@RestController
@RequestMapping("/dishes")
public class DishesController {

    @Autowired
    DishesService dishesService;

    /**
     * 添加菜品
     */
    @PostMapping
    public ResultVO<Object> add(@Valid @RequestBody Dishes dishes) {
        dishesService.add(dishes);
        return ResultVO.success();
    }

    @GetMapping("/list")
    public ResultVO<PageVO<DishesVO>> list(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            Dishes dishes) {
        PageVO<DishesVO> pageVO = dishesService.pageList(pageNum, pageSize, dishes);
        return ResultVO.success(pageVO);
    }

    @PostMapping("/{id}")
    public ResultVO<Object> update(@PathVariable Integer id, @RequestBody Dishes dishes) {
        dishesService.update(id, dishes);
        return ResultVO.success();
    }

    /**
     * 删除菜品 post /dishes/delete?id=x
     */
    @PostMapping("/delete")
    public ResultVO<Object> delete(int id) {
        dishesService.delete(id);
        return ResultVO.success();
    }
}

