package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Ingredients;

import java.util.List;

public interface IngredientsService extends IService<Ingredients> {
    /**
     * 添加食材
     *
     * @param ingredients 食材参数
     */
    void add(Ingredients ingredients);

    /**
     * 查询食材列表
     *
     * @param ingredients
     * @return
     */
    List<Ingredients> getList(Ingredients ingredients);

    PageVO<Ingredients> listPage(int pageNum, int pageSize, Ingredients ingredients);

    /**
     * 更新食材
     *
     * @param id          食材id
     * @param ingredients 其他更新参数
     */
    void update(int id, Ingredients ingredients);
}
