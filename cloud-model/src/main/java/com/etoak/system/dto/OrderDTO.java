package com.etoak.system.dto;

import com.etoak.system.entity.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class OrderDTO {
    @NotBlank(message = "prepareTime 不能为空")
    private String prepareTime;

    @NotEmpty(message = "itemList 不能为空")
    @Valid
    List<OrderItem> itemList;
}
