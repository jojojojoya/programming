package com.jojonezip.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jojonezip.demo.vo.ProductVO;


@Mapper
public interface IndexProductMapper {

	
	@Select("SELECT * FROM \"product\"")
	List<ProductVO> productListing();
	
	@Select("SELECT * FROM \"product\" WHERE product_category = #{category}")
	List<ProductVO> productListingByCategory(String category);

	@Select("SELECT * FROM \"product\" WHERE product_id = #{productId}")
	ProductVO showProductDetail(int productId);

}
