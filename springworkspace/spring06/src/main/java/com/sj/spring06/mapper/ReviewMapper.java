package com.sj.spring06.mapper;

import com.sj.spring06.vo.ProductVO;
import com.sj.spring06.vo.ReviewVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("select * from review_test order by r_date desc ")
    List<ReviewVO> getReviews();

    @Insert("INSERT INTO review_test VALUES (review_test_seq.nextval,#{r_title}, #{r_txt},sysdate)")
    void addReview(ReviewVO reviewVO);



    @Select("select * from review_test where r_no = #{no}")
    ReviewVO viewReview(int no);


        @Delete("delete review_test where r_no = #{no}")
        void deleteReview(int no);


    @Select("select * from review_test where r_no = #{no}")
    ReviewVO viewReviewDetail(int no);


    @Update("update review_test set r_title = #{r_title}, r_txt=#{r_txt} where r_no= #{r_no}")
    void updateReviewComplete(ReviewVO reviewVO);
}
