package com.jojonezip.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.vo.QnaVO;
import com.jojonezip.demo.vo.ReviewVO;

@Mapper
public interface QnaMapper {

	@Select("SELECT * FROM \"qna\" WHERE product_id = #{productId}")
	List<QnaVO> getQnaByProductId(@Param("productId") int productId);
	
	@Select("SELECT q.qna_id, q.user_id, q.qna_title, q.qna_text, q.qna_date, q.qna_answer, q.product_id, p.product_name, p.product_image FROM \"qna\" q JOIN \"product\" p ON q.product_id = p.product_id WHERE q.user_id = #{userId}")
	List<QnaVO> getQnaByUserId(@Param("userId") String userId);
	
	@Select("""
		    SELECT q.qna_id, q.user_id, q.product_id, q.qna_date, q.qna_title, q.qna_text, q.qna_answer,
		           p.product_name, p.product_image
		    FROM "qna" q
		    JOIN "product" p ON q.product_id = p.product_id
		    WHERE q.qna_id = #{qnaId}
		""")
		QnaVO getQnaByQnaId(@Param("qnaId") int qnaId);
	
	@Delete("DELETE FROM qna WHERE qna_id = #{qnaId}")
	int deleteQnaById(@Param("qnaId") int qnaId);

	
	@Update("UPDATE qna SET qna_title = #{qna_title}, qna_text = #{qna_text} WHERE qna_id = #{qna_id}")
	int updateQnaById(QnaVO qna);
	

	@Insert("INSERT INTO qna (user_id, qna_title, qna_text, qna_date, product_id) " +
	        "VALUES (#{user_id}, #{qna_title}, #{qna_text}, CURRENT_DATE, #{product_id})")
	int insertQnaById(QnaVO qna);
	
}
