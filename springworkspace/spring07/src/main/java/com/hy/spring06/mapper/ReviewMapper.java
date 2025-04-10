package com.hy.spring06.mapper;

import com.hy.spring06.vo.ReviewVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("select * from review_test order by r_date desc")
    List<ReviewVO> getReviews();

    @Insert("insert into review_test values (review_test_seq.nextval, #{r_title}, #{r_txt}, sysdate)")
    void addReview(ReviewVO reviewVO);

    @Select("select * from review_test where r_no = #{r_no}")
    ReviewVO getReview(int no);

    @Delete("delete review_test where r_no = #{no}")
    void delReview(int no);

    @Update("update review_test set r_title = #{r_title}, r_txt = #{r_txt}, r_date = sysdate where r_no = #{r_no}")
    int updateReview(ReviewVO reviewVO);
}
