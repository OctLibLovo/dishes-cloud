package com.etoak.common.web.handler;

import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @ControllerAdvice + @ResponseBody
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResultVO<Object> handle(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        // [{defaultMessage = "name 必填"}, {defaultMessage = "age 必填"}]
        List<ObjectError> allErrors = e.getAllErrors();

        // [{defaultMessage = "name 必填"}, {defaultMessage = "age 必填"}] => "name 必填；age 必填"
        String message = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("；"));
        return ResultVO.failed(message);
    }

    @ExceptionHandler
    public ResultVO<Object> handle(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultVO.failed("请求方法错误！");
    }

    @ExceptionHandler
    public ResultVO<Object> handle(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultVO.failed("请求参数类型错误！");
    }


    @ExceptionHandler
    public ResultVO<Object> handle(ParamException e) {
        log.error(e.getMessage(), e);
        return ResultVO.failed(e.getMessage());
    }

    /**
     * 处理其它异常
     */
    @ExceptionHandler
    public ResultVO<Object> handle(Exception e) {
        log.error(e.getMessage(), e);
        return ResultVO.failed();
    }
}
