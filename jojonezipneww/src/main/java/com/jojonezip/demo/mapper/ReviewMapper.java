package com.jojonezip.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jojonezip.demo.vo.ReviewVO;

@Mapper
public interface ReviewMapper {

	@Select("""
			SELECT review_id, user_id, product_id, review_date, review_title, review_text, review_image, review_answer
			FROM "review"
			WHERE product_id = #{productId}
		""")
		List<ReviewVO> getReviewsByProductId(@Param("productId") int productId);

	
	
	@Select("""
		    SELECT r.review_id, r.user_id, r.product_id, r.review_date, r.review_title, r.review_text,r.review_answer,
		           p.product_name, p.product_image
		    FROM "review" r
		    JOIN "product" p ON r.product_id = p.product_id
		    WHERE r.user_id = #{userId}
		""")
	List<ReviewVO> getReviewsByUserId(@Param("userId") String userId);
	
	@Select("""
SELECT r.review_id, r.user_id, r.product_id, r.review_date, r.review_title, r.review_text, r.review_image, r.review_answer,
       p.product_name, p.product_image
FROM "review" r
JOIN "product" p ON r.product_id = p.product_id
WHERE r.review_id = #{reviewId}

		""")
		ReviewVO getReviewByReviewId(@Param("reviewId") int reviewId);
	
	@Select("""
		    SELECT COUNT(*) 
		    FROM review
		    WHERE user_id = #{userId} AND product_id = #{productId}
		""")
		int hasWrittenReview(@Param("userId") String userId, @Param("productId") int productId);

	
	@Delete("DELETE FROM review WHERE review_id = #{reviewId}")
	int deleteReviewById(@Param("reviewId") int reviewId);
	
	@Update("""
		    UPDATE review
		    SET review_title = #{review_title}, review_text = #{review_text}, review_image = #{review_image}
		    WHERE review_id = #{review_id}
		""")
		int updateReview(ReviewVO review);
	
	@Insert("INSERT INTO review (user_id, review_title, review_text, review_date, product_id, review_image) VALUES (#{user_id}, #{review_title}, #{review_text}, CURRENT_DATE, #{product_id}, #{review_image})")
	int insertReviewById(ReviewVO review);
	
	
	@Select("""
		    SELECT r.review_id, r.user_id, r.product_id, r.review_date,
		           r.review_title, r.review_text, r.review_image, r.review_answer,
		           p.product_name, p.product_image
		    FROM review r
		    JOIN product p ON r.product_id = p.product_id
		    WHERE r.review_id = #{reviewId}
		""")
		ReviewVO getReviewById(@Param("reviewId") int reviewId);

}
