package com.hy.spring06.mapper;

import com.hy.spring06.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 반환자료형
    // 결과 여러개 -> List
    // 하나의 결과 -> DTO, VO, POJO, Bean
    // 수정, 삭제, 생성 -> int or void(반환받고 싶은게 업을 때)

    // 전체 조회
    List<ProductVO> getProducts();

    int addProduct(ProductVO productVO);

    int delProduct(int pk);

    int modiProduct(ProductVO productVO);


    @Select("select * from product_test2 where p_no=#{no}")
    ProductVO getProduct(int no);


    int updateProduct(ProductVO productVO);
}
