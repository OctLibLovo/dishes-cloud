package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author etoak
 * @since 2026-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 菜品id
     */
    @NotNull(message = "dishesId 不能为空")
    private Integer dishesId;

    /**
     * 菜品数量
     */
    @NotNull(message = "dishesNum 不能为空")
    @Min(value = 1, message = "dishesNum 最小值为1")
    private Integer dishesNum;

}
