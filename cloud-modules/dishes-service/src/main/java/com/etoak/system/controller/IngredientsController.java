package com.etoak.system.controller;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Ingredients;
import com.etoak.system.service.IngredientsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
