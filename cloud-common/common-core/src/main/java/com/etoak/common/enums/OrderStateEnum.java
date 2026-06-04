package com.etoak.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStateEnum {
    /**
     * 新建
     */
    NEW(1),
    /**
     * 支付
     */
    PAY(2),
    /**
     * 取消
     */
    CANCEL(3);

    private Integer value;
}
