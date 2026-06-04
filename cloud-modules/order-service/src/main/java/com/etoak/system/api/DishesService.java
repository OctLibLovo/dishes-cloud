package com.etoak.system.api;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.vo.DishesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "dishes-service")
public interface DishesService {

    /**
     * 根据id列表查询菜品列表
     */
    @GetMapping("/dishes/{ids}")
    ResultVO<List<DishesVO>> listByIds(@PathVariable List<Integer> ids);
}
