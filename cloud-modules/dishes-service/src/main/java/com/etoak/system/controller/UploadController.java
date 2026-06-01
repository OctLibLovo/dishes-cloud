package com.etoak.system.controller;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.api.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping("/image")
    public ResultVO<String> upload(MultipartFile pic){
        return uploadService.uploadImage(pic);
    }
}
