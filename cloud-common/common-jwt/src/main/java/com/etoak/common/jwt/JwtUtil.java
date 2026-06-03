package com.etoak.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String KEY_STRING = "11111111111111111111111111111111";

    /**
     * 参数是字节数组
     * 32字节 = 256 bit
     */
    public static final Key KEY = Keys.hmacShaKeyFor(KEY_STRING.getBytes());

    public static final long EXPIRE_MILLIS = 1000 * 60 * 60 * 12;

    public static String create(Map<String, Object> claimsMap) {
        // 签发时间
        Date issueDate = new Date();
        // 过期时间
        Date expireDate = new  Date(issueDate.getTime() + EXPIRE_MILLIS);

        return Jwts.builder()
                .signWith(KEY) //签名密钥
                .setClaims(claimsMap)
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .compact();
    }

    public static Map<String, Object> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
