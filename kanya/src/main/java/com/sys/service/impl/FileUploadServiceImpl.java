package com.sys.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sys.base.exception.BaseException;
import com.sys.base.tool.FileUtils;
import com.sys.base.tool.ImageUtils;
import com.sys.entity.File;
import com.sys.response.AppResponse;
import com.sys.service.FileUploadService;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

	public AppResponse fileUpload(String path, MultipartFile[] file) {
		return fileUpload1(path, file);
	}

	public AppResponse fileUpload1(String subpath, MultipartFile[] file) {
		try {
			ArrayList<Object> list = new ArrayList<Object>(file.length);
			if (file != null && file.length > 0) {
				for (MultipartFile multipartFile : file) {
					File f = writeUploadFile(multipartFile, subpath);
					String oName = multipartFile.getOriginalFilename();
					Map<String, Object> fileResp = new HashMap<>();
					fileResp.put("oldName", oName);
					fileResp.put("path", f.getPath());
					list.add(fileResp);
				}
			}
			return AppResponse.okData(list);
		} catch (Exception e) {
			throw new BaseException("上传文件出错");
		}
	}

	public File writeUploadFile(MultipartFile mf, String subpath)
			throws IOException {
		byte[] filedata = mf.getBytes();
		File file = null;

		// if (file == null) {
		file = new File();
		file.setSourceName(mf.getOriginalFilename()); // 原始文件名
		// file.setMd5(md5); // 文件md5签名
		// file.setFileType(FileTypeDict.getFileExtension(filedata)); //
		// 文件类型

		//TODO
//	    String path = "E:/photo/";
		String path="/javaEnv/photo/";
		path = path + subpath + "/";
		String originPath = path;
		String filename = FileUtils.writeFile(originPath, filedata);
		System.out.println("原文件名："+filename);
		int pos;
		String zippedName = (pos = filename.lastIndexOf(".")) >= 0 ? filename
				.substring(0, pos) + "_z." + filename.substring(1 + pos)
				: filename + "_z.";
		System.out.println("文件名："+zippedName);
		ImageUtils.zoomImageScale(new java.io.File(originPath + filename),
				originPath + zippedName, 0);

		file.setPath( subpath + "/" + filename); // 文件路径
		
		// }
		return file;
	}

}
