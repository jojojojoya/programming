package com.koyoi.main.mapper;

import com.koyoi.main.vo.QuoteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuoteMapper {


    @Select("SELECT Q.quote_id, Q.admin_id, U.user_id, Q.content, Q.created_at " +
            "FROM TEST_QUOTE Q LEFT JOIN TEST_USER U ON Q.admin_id = U.user_id")
    List<QuoteVO> getAllQuotes();



}
