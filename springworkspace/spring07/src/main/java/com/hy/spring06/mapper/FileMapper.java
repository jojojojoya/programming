package com.hy.spring06.mapper;

import com.hy.spring06.vo.FileVO;
import com.hy.spring06.vo.ReviewVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("insert into file_test values (file_test_seq.nextval,#{f_title},#{f_name})")
    void saveFile(FileVO fileVO);

    @Insert("insert into file_test2 values (#{fileNames})")
    void saveFiles(String fileNames);
    }

