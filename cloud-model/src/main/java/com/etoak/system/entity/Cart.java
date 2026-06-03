package com.etoak.system.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Cart {

    @NotNull(message="dishesId不能为空")
    private Integer dishesId;

    @NotNull(message = "count 不能为空")
    @Min(value = 1, message = "count 最小值为1")
    private Integer count;

    private String dishesName;
}
