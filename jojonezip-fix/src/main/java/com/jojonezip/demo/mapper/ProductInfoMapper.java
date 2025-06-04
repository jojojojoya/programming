package com.jojonezip.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jojonezip.demo.vo.ProductInfoVO;

@Mapper
public interface ProductInfoMapper {
	
	@Select("SELECT * FROM \"product_info\" WHERE product_id = #{productId}")
	List<ProductInfoVO> getInfoByProductId(@Param("productId") int productId);
	
	
}