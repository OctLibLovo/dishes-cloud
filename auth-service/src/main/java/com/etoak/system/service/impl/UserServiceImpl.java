package com.etoak.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.etoak.common.contant.CommonConstant;
import com.etoak.common.exception.ParamException;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.redis.RedisService;
import com.etoak.system.dto.LoginDTO;
import com.etoak.system.entity.User;
import com.etoak.system.mapper.UserMapper;
import com.etoak.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.system.vo.TokenVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RedisService redisService;

    @Override
    public TokenVO login(LoginDTO loginDTO) {
        //获取 redis 中的验证码
        String code = redisService.get(CommonConstant.CODE_KEY_PREFIX + loginDTO.getUuid());
        if(!Strings.CI.equals(code, loginDTO.getCode())){
            throw new ParamException("验证码错误！");
        }
        String password = MD5.create().digestHex(loginDTO.getPassword());
        User user = lambdaQuery().eq(User::getUsername, loginDTO.getUsername())
                .eq(User::getPassword, password)
                .one();
        if (ObjectUtils.isEmpty(user)) {
            throw new ParamException("用户名或密码错误！");
        }

        Map<String,Object> claimsMap = new HashMap<>();
        claimsMap.put(CommonConstant.LOGIN_USERNAME, user.getUsername());
        claimsMap.put(CommonConstant.LOGIN_USER_ID, user.getId());
        String jwt = JwtUtil.create(claimsMap);

        return new TokenVO(user.getUsername(), jwt);
    }
}
