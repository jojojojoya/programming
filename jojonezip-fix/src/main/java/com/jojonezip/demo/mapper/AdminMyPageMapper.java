package com.jojonezip.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.ProductVO;
import com.jojonezip.demo.vo.QnaVO;
import com.jojonezip.demo.vo.ReviewVO;
import com.jojonezip.demo.vo.UserVO;

@Mapper
public interface AdminMyPageMapper {

	@Select("SELECT * FROM \"user\"")
	List<UserVO> showUserList();

	@Select("SELECT * FROM \"product\"")
	List<ProductVO> showProductList();

	@Select("SELECT q.qna_id, q.user_id, q.qna_title, q.qna_text, q.qna_date, q.qna_answer, q.product_id, p.product_name, p.product_image FROM \"qna\" q JOIN \"product\" p ON q.product_id = p.product_id")
	List<QnaVO> showQnaList();

	@Select("""
				    SELECT r.review_id, r.user_id, r.product_id, r.review_date, r.review_title, r.review_text,r.review_answer,
			           p.product_name, p.product_image
			    FROM "review" r
			    JOIN "product" p ON r.product_id = p.product_id
			""")
	List<ReviewVO> showReviewList();

	@Select("SELECT COUNT(*) FROM \"user\"")
	int getUserCount();

	@Select("SELECT COUNT(*) FROM \"review\" WHERE review_date = CURRENT_DATE")
	int getReviewCount();

	@Select("SELECT COUNT(*) FROM \"qna\" WHERE qna_date = CURRENT_DATE")
	int getQnaCount();
	
	
	@Select("""
			SELECT COUNT(*) 
			FROM orders 
			WHERE order_date::date = CURRENT_DATE 
			AND (order_status = '결제완료' OR order_status = '배송 처리완료')
		""")
		int getOrdersCount();

		@Select("""
			SELECT COALESCE(SUM(oi.product_price * oi.product_amount), 0)
			FROM orders o
			JOIN order_items oi ON o.order_id = oi.order_id
			WHERE o.order_date::date = CURRENT_DATE 
			AND (o.order_status = '결제완료' OR o.order_status = '배송 처리완료')
		""")
		int getUriageCount();


	@Delete("DELETE FROM \"user\" WHERE user_id = #{userId}")
	int deleteUser(String userId);

	@Insert("INSERT INTO \"user\" (user_id, user_password, user_nickname, user_type) VALUES (#{userId}, #{userPassword}, #{userNickname}, 1)")
	int addUser(@Param("userId") String userId, @Param("userPassword") String userPassword,
			@Param("userNickname") String userNickname);

	@Update("""
			  UPDATE review
			  SET review_answer = #{reviewAnswer}
			  WHERE review_id = #{reviewId}
			""")
	int insertReviewAnswer(@Param("reviewAnswer") String reviewAnswer, @Param("reviewId") int reviewId);

	@Update("UPDATE \"review\" SET review_answer = NULL WHERE review_id = #{reviewId}")
	int deleteReviewAnswer(@Param("reviewId") int reviewId);

	@Update("""
			  UPDATE qna
			  SET qna_answer = #{qnaAnswer}
			  WHERE qna_id = #{qnaId}
			""")
	int insertQnaAnswer(@Param("qnaAnswer") String qnaAnswer, @Param("qnaId") int qnaId);

	@Update("UPDATE \"qna\" SET qna_answer = NULL WHERE qna_id = #{qnaId}")
	int deleteQnaAnswer(@Param("qnaId") int qnaId);

	@Insert("""
			    INSERT INTO "product" (
			        product_category, product_name, product_price,
			        product_amount, product_introtext, product_image
			    )
			    VALUES (
			        #{product_category}, #{product_name}, #{product_price},
			        #{product_amount}, #{product_introtext}, #{product_image}
			    )
			""")
	@Options(useGeneratedKeys = true, keyProperty = "product_id")
	int addProduct(ProductVO product);

	@Insert("""
			    INSERT INTO "product_info" (product_id, info_content, info_date)
			    VALUES (#{productId}, #{infoContent}, CURRENT_TIMESTAMP)
			""")
	int addProductInfo(@Param("productId") int productId, @Param("infoContent") String infoContent);

	@Select("SELECT p.*, pi.info_content AS product_infocontent FROM product p LEFT JOIN product_info pi ON p.product_id = pi.product_id WHERE p.product_id = #{productId}")

	ProductVO getProductById(int productId);

	@Update("""
			    UPDATE product
			    SET product_name = #{product_name},
			        product_price = #{product_price},
			        product_amount = #{product_amount},
			        product_category = #{product_category},
			        product_introtext = #{product_introtext},
			        product_image = #{product_image}
			    WHERE product_id = #{product_id}
			""")
	int updateProduct(ProductVO product);

	@Delete("DELETE FROM product WHERE product_id = #{productId}")
	int deleteProductById(int productId);

	@Update("""
			    UPDATE product_info
			    SET info_content = #{infoContent}, info_date = CURRENT_TIMESTAMP
			    WHERE product_id = #{productId}
			""")
	int updateProductInfo(@Param("productId") int productId, @Param("infoContent") String infoContent);

	@Select("SELECT o.order_id, o.user_id, o.order_date, o.order_status, p.product_name, p.product_image FROM orders o JOIN order_items oi ON o.order_id = oi.order_id JOIN product p ON oi.product_id = p.product_id ORDER BY o.order_date DESC")
	List<Map<String, Object>> getOrder();

	
	@Select("""
		    SELECT 
		        order_id AS orderId,
		        user_id AS userId,
		        order_name AS orderName,
		        order_tel AS orderTel,
		        order_address AS orderAddress,
		        order_date AS orderDate,
		        order_status AS orderStatus
		    FROM orders
		    WHERE order_id = #{orderId}
		""")
		OrderVO getOrderSummaryByOrderIdSingle(@Param("orderId") int orderId);
	
	@Select("""
		    SELECT
			    o.order_id,
			    o.user_id,
			    o.order_date,
			    o.order_status,
			    o.order_name,
			    o.order_tel,
			    o.order_address,
		        p.product_name,
		        p.product_image,
		        oi.product_amount,
		        oi.product_price 
		    FROM orders o
		    JOIN order_items oi ON o.order_id = oi.order_id 
		    JOIN product p ON oi.product_id = p.product_id
		    WHERE oi.order_id = #{orderId}
		""")
	
	
List<Map<String, Object>> getOrderDetailByOrderId(@Param("orderId") int orderId);

 @Update("UPDATE orders SET order_status = #{orderStatus} WHERE order_id = #{orderId}")
 void updateOrderStatusToCancel(@Param("orderId") int orderId, @Param("orderStatus") String orderStatus);
 
 @Update("UPDATE orders SET order_status = #{orderStatus} WHERE order_id = #{orderId}")
 void updateOrderStatusToDelivered(@Param("orderId") int orderId, @Param("orderStatus") String orderStatus);

}
