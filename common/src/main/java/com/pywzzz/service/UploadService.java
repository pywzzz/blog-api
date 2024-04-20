package com.pywzzz.service;

import com.pywzzz.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}