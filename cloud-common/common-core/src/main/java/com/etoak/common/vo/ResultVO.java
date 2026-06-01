package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 执行成功后的响应码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 执行成功后的响应消息
     */
    public static final String SUCCESS_MESSAGE = "success.";

    /**
     * 执行失败后的默认响应码
     */
    public static final int DEFAULT_FAILED_CODE = 500;

    /**
     * 执行失败后的默认响应消息
     */
    public static final String DEFAULT_FAILED_MESSAGE = "服务内部错误！";

    /**
     * 拒绝访问响应码
     */
    public static final int FORBIDDEN_CODE = 403;

    private Integer code;

    private String message;

    private T data;

    /**
     * 添加、删除、更新等不需要返回数据的接口调用
     */
    public static <T> ResultVO<T> success() {
        return success(null);
    }

    /**
     * 查询接口等需要返回数据的接口调用
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ResultVO<T> failed() {
        return failed(DEFAULT_FAILED_MESSAGE);
    }

    public static <T> ResultVO<T> failed(String message) {
        return failed(DEFAULT_FAILED_CODE, message);
    }

    public static <T> ResultVO<T> failed(int code, String message) {
        return new ResultVO<>(code, message, null);
    }
}