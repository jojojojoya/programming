package com.jojonezip.demo.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.ProductInfoMapper;
import com.jojonezip.demo.vo.ProductInfoVO;

@Service
public class ProductInfoService {

	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	
	public List<ProductInfoVO> getInfoByProductId(int productId) {
	return productInfoMapper.getInfoByProductId(productId);	
	}
	

}
