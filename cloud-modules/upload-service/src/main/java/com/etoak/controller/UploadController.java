package com.etoak.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.exception.ParamException;
import com.etoak.common.properties.ImageProperties;
import com.etoak.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    ImageProperties properties;

    @PostMapping("/image")
    public ResultVO<String> uploadImage(MultipartFile file){
        //1. 判断是否是null  or 空文件
        if(ObjectUtil.isEmpty(file) || file.isEmpty()){
            throw new ParamException("上传失败 文件不能为空");
        }
        //2. 校验类型是否合法【jpeg png】
            // 判断 + 判断不通过告诉客户端合法的类型
        List<String> typeList = properties.getTypeList();
        if(!typeList.contains(file.getContentType())){
            String string = typeList.stream().map(type -> type.substring(type.lastIndexOf(StrUtil.SLASH) + 1))
                    .collect(Collectors.joining(","));
            throw new ParamException("上传失败，仅支持" + string);
        }
        //3. 位置是否存在【dir】
        File parent = new File(properties.getLocation());
        parent.mkdirs();

        //4. 获取文件名称=》后缀
        String oldName = file.getOriginalFilename();
        String suffix = oldName.substring(oldName.lastIndexOf(StrUtil.DOT));

        //5. uuid.后缀
        String newName = UUID.randomUUID() + suffix;
        File targetFile = new File(parent, newName);
        //6. 上传 transferTo(目标文件)
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //7. 返回  /pic/uuid.后缀
        String result = properties.getPrefix().endsWith(StrUtil.SLASH)
                ? properties.getPrefix() + newName
                : properties.getPrefix() + StrUtil.SLASH + newName;


        return ResultVO.success(result);
    }
}
