package com.etoak.common.web.context;

import com.etoak.common.contant.CommonConstant;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.web.uril.ServletUtil;
import org.springframework.http.HttpHeaders;


import java.util.Map;

public class LoginUserContext {
    public static String getId() {
        return getUserMap().get(CommonConstant.LOGIN_USER_ID).toString();
    }
    public static String getUsername() {
        return getUserMap().get(CommonConstant.LOGIN_USERNAME).toString();
    }
    private static Map<String, Object> getUserMap(){
        String header = ServletUtil.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        return JwtUtil.parse(header);
    }
}
