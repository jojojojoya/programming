package com.jojonezip.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.IndexProductMapper;
import com.jojonezip.demo.vo.ProductVO;

@Service
public class IndexProductService {

	@Autowired
	private IndexProductMapper indexProductMapper;
	
	public List<ProductVO> productListing() {
		return indexProductMapper.productListing();
	}
	public List<ProductVO> productListingByCategory(String category) {
		return indexProductMapper.productListingByCategory(category);
	}
	
	
	//단일 상품조회
	public ProductVO showProductDetail(int productId) {
		return indexProductMapper.showProductDetail(productId);
	}
}
