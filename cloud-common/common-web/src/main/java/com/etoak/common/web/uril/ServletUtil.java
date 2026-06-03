package com.etoak.common.web.uril;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Servlet 工具类 主要获取 request、responst 对象
 */
public class ServletUtil {
    public static HttpServletRequest getRequest() {
        return getAttributes().getRequest();
    }
    public static HttpServletResponse getResponse() {
        return getAttributes().getResponse();
    }
    public static ServletRequestAttributes getAttributes() {
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }
}
