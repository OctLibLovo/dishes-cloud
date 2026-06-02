package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 菜品实体类
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
@Data
@TableName("t_dishes")
public class Dishes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜品名称
     */
    @NotBlank(message = "nama 不能为空")
    private String name;

    /**
     * 菜品图片
     */
    @NotBlank(message = "url 不能为空")
    private String url;

    /**
     * 主要食材的id
     */
    @NotNull(message = "主要食材 main 不能为空")
    private Integer main;

    /**
     * 主要食材份量
     */
    @NotNull(message = "主要食材分量不能为空")
    private Integer mainNum;

    /**
     * 辅助食材的id
     */
    @NotNull(message = "辅助食材 minor 不能为空")
    private Integer minor;

    /**
     * 辅助食材份量
     */
    @NotNull(message = "辅助食材分量不能为空")
    private Integer minorNum;

    /**
     * 配料的id
     */
    @NotNull(message = "配料 seasoning 不能为空")
    private Integer seasoning;

    /**
     * 配料份量
     */
    @NotNull(message = "配料分量不能为空")
    private Integer seasoningNum;

    /**
     * 菜品描述
     */

    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;
}
