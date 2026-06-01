package com.etoak.system.controller;

import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Ingredients;
import com.etoak.system.service.IngredientsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    @Autowired
    IngredientsService ingredientsService;

    /**
     * 添加食材接口 post /ingredients
     */
    @PostMapping
    public ResultVO<Object> add(@Valid @RequestBody Ingredients ingredients){
        ingredientsService.add(ingredients);
        return ResultVO.success();
    }

    /**
     * 分页查询 get /ingredients/list
     */
    @GetMapping("/list")
    public ResultVO<PageVO<Ingredients>> listPage(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "1") int pageSize,
            Ingredients ingredients) {
        PageVO<Ingredients> pageVO = ingredientsService.listPage(pageNum, pageSize, ingredients);
        return ResultVO.success(pageVO);
    }

    @PostMapping("/{id}")
    public ResultVO<Object> update(@PathVariable Integer id, @Valid @RequestBody Ingredients ingredients){
        ingredientsService.update(id, ingredients);
        return ResultVO.success();
    }

    /**
     * 删除食材接口 post /ingredinents/delete?id=x
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO<Object> delete(int id) {
        ingredientsService.delete(id);
        return ResultVO.success();
    }
}
