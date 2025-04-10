package com.sj.spring06.mapper;


import com.sj.spring06.vo.ProductVO;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ProductMapper {
    // 반환자료형
    // 결과 여러개 -> list
    // 하나의 결과 - dto,vo ,pojo ,bean
    // 수정,삭제,생성-  int or void
    List<ProductVO> getProducts();
    int addProduct(ProductVO productVO);
    int deleteProduct(int productVO);
    int updateProduct(int productVO);

}
