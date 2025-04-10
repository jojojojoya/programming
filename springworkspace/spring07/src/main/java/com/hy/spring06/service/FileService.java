package com.hy.spring06.service;

import com.hy.spring06.mapper.FileMapper;
import com.hy.spring06.mapper.ReviewMapper;
import com.hy.spring06.vo.FileVO;
import com.hy.spring06.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    public FileMapper fileMapper;
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void upload(FileVO fileVO, MultipartFile file) throws IOException {
    //업로드
        String originName = file.getOriginalFilename();

        String fileExtension =
        originName.substring(originName.lastIndexOf("."),originName.length());

        System.out.println(fileExtension);
        String uploadFolder = "C:\\Windows\\sdfsd";

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String[] uuids =
                uuid.toString().split("-");
        System.out.println(uuids[0]);
        String fileName =
                uuids[0] + fileExtension;
        File saveFile = new File(uploadFolder + "\\" + fileName);

try {
    file.transferTo(saveFile);
    fileVO.setF_name(fileName);


    fileMapper.saveFile(fileVO);
} catch (IOException e) {
    throw new RuntimeException(e);
}
    }

    public void upload2(MultipartFile[] files) {
        String uploadFolder = "C:\\Windows\\sdfsd";

        String fileNames = "";
        for (MultipartFile file : files) {
            //확장자 추출
            String originName = file.getOriginalFilename();
            String fileExtension = originName.substring(originName.lastIndexOf("."), originName.length());


            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
            String[] uuids =
                    uuid.toString().split("-");
            System.out.println(uuids[0]);
            String fileName =
                    uuids[0] + fileExtension;
            fileNames += fileName + "!";
            File saveFile = new File(uploadFolder + "\\" + fileName);

            try {
                file.transferTo(saveFile);
                //다중 업로드?

            } catch (Exception e) {
            }
        }
        fileMapper.saveFiles(fileNames);
    }}
