package com.etoak.system.controller;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Dict;
import com.etoak.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    DictService dictService;

    /**
     * 根据类型查询字典列表
     * @param type  必传
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<Dict>> getDictsByType(@RequestParam String type){
        List<Dict> dictList = dictService.getList(type);
        return ResultVO.success(dictList);
    }

}
