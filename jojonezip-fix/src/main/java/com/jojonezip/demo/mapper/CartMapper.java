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

import com.jojonezip.demo.vo.CartVO;
import com.jojonezip.demo.vo.OrderVO;
import com.jojonezip.demo.vo.OrderitemVO;
import com.jojonezip.demo.vo.QnaVO;

@Mapper
public interface CartMapper {

	@Select("""
			SELECT
			    c.user_id AS userId,
			    c.product_id AS productId,
			    c.quantity,
			    p.product_name AS productName,
			    p.product_image AS productImage,
			    p.product_price AS productPrice
			FROM
			    cart c
			JOIN
			    product p ON c.product_id = p.product_id
			WHERE
			    c.user_id = #{userId}
			""")
	List<CartVO> getCartListByUserId(String userId);

	@Select("SELECT * FROM cart WHERE user_id = #{userId} AND product_id = #{productId}")
	CartVO findCartItem(@Param("userId") String userId, @Param("productId") int productId); // ✅ @Param 추가

	@Insert("INSERT INTO cart(user_id, product_id, quantity) VALUES (#{userId}, #{productId}, #{quantity})")
	void insertCartItem(@Param("userId") String userId, @Param("productId") int productId,
			@Param("quantity") int quantity);

	@Update("UPDATE cart SET quantity = #{quantity} WHERE user_id = #{userId} AND product_id = #{productId}")
	void updateQuantity(@Param("userId") String userId, @Param("productId") int productId,
			@Param("quantity") int quantity);

	@Delete("DELETE FROM cart WHERE user_id = #{userId} AND product_id = #{productId}")
	void deleteCartItem(@Param("userId") String userId, @Param("productId") int productId);

	@Insert("""
			    INSERT INTO orders (user_id, order_name, order_tel, order_address)
			    VALUES (#{userId}, #{orderName}, #{orderTel}, #{orderAddress})
			""")

	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	void insertOrder(OrderVO order);

	@Insert("""
			    INSERT INTO order_items (order_id, product_id, product_amount, product_price)
			    VALUES (#{orderId}, #{productId}, #{productAmount}, #{productPrice})
			""")
	void insertOrderItem(OrderitemVO orderItem);

	@Select("""
			    SELECT
			        o.order_id,
			        o.user_id,
			        o.order_date,
			        o.order_status,
			        p.product_name,
			        p.product_image
			    FROM orders o
			    JOIN order_items oi ON o.order_id = oi.order_id
			    JOIN product p ON oi.product_id = p.product_id
			    WHERE o.user_id = #{userId}
			    ORDER BY o.order_date DESC
			""")
	List<Map<String, Object>> getOrderSummaryByUserId(@Param("userId") String userId);

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
	
	
	@Update("UPDATE orders SET order_status = #{orderStatus} WHERE order_id = #{orderId}")
	void updateOrderStatus(@Param("orderId") int orderId, @Param("orderStatus") String orderStatus);

	@Select("""
		    SELECT COUNT(*) 
		    FROM orders o
		    JOIN order_items oi ON o.order_id = oi.order_id
		    WHERE o.user_id = #{userId}
		      AND oi.product_id = #{productId}
		      AND (o.order_status = '결제완료' OR o.order_status = '배송 처리완료')
		""")
		int hasPurchasedProduct(@Param("userId") String userId, @Param("productId") int productId);  // ✅ 이렇게!

	@Select("""
		    SELECT 
		        COALESCE(SUM(oi.product_amount), 0) AS total_count,
		        COALESCE(SUM(oi.product_amount * oi.product_price), 0) AS total_price
		    FROM orders o
		    JOIN order_items oi ON o.order_id = oi.order_id
		    WHERE o.user_id = #{userId}
		      AND o.order_status = '배송 처리완료'
		""")
		Map<String, Object> getTotalCountAndPriceByUserId(@Param("userId") String userId);

	
	
	@Select("SELECT product_amount FROM product WHERE product_id = #{productId}")
	int getProductStock(@Param("productId") int productId);
	
	@Update("UPDATE product SET product_amount = product_amount - #{amount} WHERE product_id = #{productId}")
	void decreaseStock(@Param("productId") int productId, @Param("amount") int amount);
}