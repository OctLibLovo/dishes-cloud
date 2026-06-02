package com.etoak.system.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.contant.CommonConstant;
import com.etoak.common.redis.RedisService;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Code;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisService redisService;

    @GetMapping("/getCode")
    public ResultVO<Code> getCode() throws IOException {
        //test = "1+1
        String text = defaultKaptcha.createText();

        String[] textArray = text.split(StrUtil.AT);

        // 将验证码结果写入redis 设置5分钟有效期
        String uuid = IdUtil.simpleUUID();
        String redisKey = CommonConstant.CODE_KEY_PREFIX + uuid;
        redisService.setex(redisKey, textArray[1], 5, TimeUnit.MINUTES);

        // 创建验证码图片
        BufferedImage bufferedImage = defaultKaptcha.createImage(textArray[0]);
        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png",  outputStream);

        String base64 = Base64.encode(outputStream.toByteArray());

        Code code = new Code(uuid, "data:image/png;base64," + base64);

        return ResultVO.success(code);
    }

}
