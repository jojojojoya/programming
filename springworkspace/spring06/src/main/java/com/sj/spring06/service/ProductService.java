package com.sj.spring06.service;

import com.sj.spring06.mapper.ProductMapper;
import com.sj.spring06.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;


    public List<ProductVO>  getProducts() {
      return productMapper.getProducts();

    }

        public void addProduct(ProductVO productVO) {
            if (productMapper.addProduct(productVO) ==1 ){
                System.out.println("수정성공");
            };
        }

    public void deleteProduct(int no) {
        // 수행했을 때 받은 값이 1 = 1행
        if (productMapper.deleteProduct(no) == 1) {
            System.out.println("삭제 성공");
        }
    }

    public void updateProduct(int no) {
        // 수행했을 때 받은 값이 1 = 1행
        if (productMapper.updateProduct(no) == 1) {
            System.out.println("업데이트 성공");
        }
    }


    }

