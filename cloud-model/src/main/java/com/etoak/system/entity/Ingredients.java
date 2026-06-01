package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@TableName("t_ingredients")
public class Ingredients {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotBlank(message = "url 不能为空")
    private String url;

    @NotBlank(message = "type 不能为空")
    @Pattern(regexp = "\\d{1}", message = "type只能是一位数字")
    private String type;

    @NotBlank(message = "description 不能为空")
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;
}
