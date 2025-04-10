package com.hy.spring06.service;

import com.hy.spring06.mapper.ProductMapper;
import com.hy.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    // 전체 조회
    public List<ProductVO> getProducts() {

        return productMapper.getProducts();

    }


    public int addProduct(ProductVO productVO) {
        return productMapper.addProduct(productVO);


    }

    public int delProduct(int pk) {
       return productMapper.delProduct(pk);
    }


    public int updateProduct(ProductVO productVO) {
        return productMapper.updateProduct(productVO);
    }

    public void modiProduct(ProductVO productVO) {
        productMapper.modiProduct(productVO);

    }

    public ProductVO getProduct(int no) {
        return productMapper.getProduct(no);


    }


}
