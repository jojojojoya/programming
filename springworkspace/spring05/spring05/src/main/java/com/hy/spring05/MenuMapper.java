package com.hy.spring05;

import org.apache.ibatis.annotations.Mapper;

import java.awt.*;
import java.util.List;

@Mapper
public interface MenuMapper {

    // select (결과가 여러개)
    // 자료 반환 List<??> (DTO)

    // select (결과가 하나)
    // dto, bean, vo, pojo

    // delete
    // 자료 반환 int

    public List<MenuDTO> getAllMenu();

    int deleteMenu(int no);

    int updateMenu(MenuDTO menuDTO);

    MenuDTO getMenu2(int no);

    MenuDTO getMenu(MenuDTO menuDTO);


    int upMenu(MenuDTO menuDTO);
}
