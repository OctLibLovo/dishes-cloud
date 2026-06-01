package com.etoak.system.api;

import com.etoak.common.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient("upload-service")
public interface UploadService {
    /**
     * 文件
     * 特殊key = value
     * 1. 参数multipart/form-data  [consumes]
     * 2. @RequestPart
     * @RequestMapping => consumes + produces
     */
    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> uploadImage(@RequestPart MultipartFile file);
}
