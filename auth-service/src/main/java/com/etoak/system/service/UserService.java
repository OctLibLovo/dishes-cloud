package com.etoak.system.service;

import com.etoak.system.dto.LoginDTO;
import com.etoak.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.system.vo.TokenVO;
import jakarta.validation.Valid;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author etoak
 * @since 2026-06-02
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    TokenVO login(@Valid LoginDTO loginDTO);
}
