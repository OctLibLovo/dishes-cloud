package com.etoak.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "username 不能为空")
    private String username;

    @NotBlank(message = "password 不能为空")
    private String password;

    @NotBlank(message = "uuid 不能为空")
    private String uuid;

    @NotBlank(message = "code 不能为空")
    private String code;
}
