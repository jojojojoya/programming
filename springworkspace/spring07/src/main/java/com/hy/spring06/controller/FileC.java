package com.hy.spring06.controller;


import com.hy.spring06.service.FileService;
import com.hy.spring06.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileC {
    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public String upload(FileVO fileVO, MultipartFile f_file) throws IOException {
        System.out.println(f_file.getOriginalFilename());
        fileService.upload(fileVO,f_file);
        return "file";
    }

    @PostMapping("upload2")
    public String upload2(MultipartFile[] files){
        fileService.upload2(files);
        return "file";
    }
}
