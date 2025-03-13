package com.koyoi.main.mapper;

import com.koyoi.main.vo.QuoteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuoteMapper {


    @Select("select * from test_quote")
    List<QuoteVO> getAllQuotes();



}
