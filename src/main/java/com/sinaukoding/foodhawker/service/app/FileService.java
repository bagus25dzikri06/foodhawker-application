package com.sinaukoding.foodhawker.service.app;

import com.sinaukoding.foodhawker.model.enums.TipeUpload;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    BaseResponse<?> upload(MultipartFile files, TipeUpload tipeUpload);
    Resource loadFileAsResource(String pathFile);
}