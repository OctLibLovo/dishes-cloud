package com.etoak.system.vo;

import com.etoak.system.entity.Dishes;
import lombok.Data;

@Data
public class DishesVO extends Dishes {

    /**
     * 主要食材
     */
    private String mainName;

    /**
     * 辅助食材
     */
    private String minorName;

    /**
     * 配料
     */
    private String seasoningName;
}
