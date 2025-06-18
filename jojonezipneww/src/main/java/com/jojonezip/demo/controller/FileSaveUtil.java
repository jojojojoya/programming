package com.jojonezip.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaveUtil {

	// 이미지 저장용 실제 경로
	private final String uploadDir = "C:/jojonezip/upload/product"; // 🔥 요걸로 바꿔!

    public String save(MultipartFile file) {
    	File folder = new File(uploadDir);
    	if (!folder.exists()) {
    	    folder.mkdirs();  // 경로 없으면 자동 생성
    	}
        if (file.isEmpty()) return null;

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir, fileName);
            file.transferTo(dest);
            return "product/" + fileName; // static/img 아래 저장, 경로는 "/img/파일명"으로 접근 가능
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }}