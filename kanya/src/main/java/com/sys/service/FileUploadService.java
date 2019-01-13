package com.sys.service;

import org.springframework.web.multipart.MultipartFile;

import com.sys.response.AppResponse;

public interface FileUploadService {

	AppResponse fileUpload(String path, MultipartFile[] file);

}
