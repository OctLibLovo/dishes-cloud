package com.etoak.system.vo;

import lombok.Data;

/**
 * 订单项详情
 */
@Data
public class OrderVO {
    private String OrderNo;

    private String dishesName;

    private Integer dishesNum;

    private String detail;

}
