package com.etoak.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.vo.ResultVO;
import com.etoak.properties.WhiteListProperties;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class TokenFilter implements GlobalFilter {
    private final WhiteListProperties whiteListProperties;

    public TokenFilter(WhiteListProperties whiteListProperties) {
        this.whiteListProperties = whiteListProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getPath().value();
        log.info("请求地址:{}", uri);
        if (whiteListProperties.getUris().contains(uri)) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isEmpty(token)) {
            return noAuth(exchange, "请传入令牌！");
        }

        try {
            JwtUtil.parse(token);
        } catch (ExpiredJwtException e) {
            return noAuth(exchange, "令牌过期");
        } catch (Exception e) {
            return noAuth(exchange, "令牌错误");
        }
        return chain.filter(exchange);
    }

    private Mono<Void> noAuth(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        ResultVO<Object> resultVO = ResultVO.failed(ResultVO.FORBIDDEN_CODE, message);
        String jsonStr = JSONUtil.toJsonStr(resultVO);

        DataBuffer dataBuffer = response.bufferFactory().wrap(jsonStr.getBytes());
        return response.writeWith(Mono.just(dataBuffer));

    }
}
