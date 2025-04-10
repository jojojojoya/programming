package com.sj.spring05_second;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

// autowired로 땡겨 쓸 수 있게
@Service
public class MenuDAO {

/*    @Autowired
    private SqlSession ss;*/

    // 1. 필드 주입. 인터페이스에 autowired. 땡기려면 땡길 게 있어야 됨. @Mapper
    //   @Autowired
    private MenuMapper menuMapper;

    // 2. 생성자 주입.
    public MenuDAO(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public void showAllMenu(Model model) {
        // 값 or db
//        MenuMapper menuMapper1 = ss.getMapper(MenuMapper.class);
//        List<MenuDTO> menus = ;
//        System.out.println(menus);
        model.addAttribute("menus", menuMapper.getAllMenu());

    }

    public void deleteMenu(int no) {

        // 수행했을 때 받은 값이 1 = 1행
        if (menuMapper.deleteMenu(no) == 1) {
            System.out.println("삭제 성공");
        }
    }
//
//    public void updateMenu(MenuDTO menuDTO) {
//
//        int row = menuMapper.upMenu( menuDTO);
//        // 값 or db => 컨트롤러에서 처리 => 바로 추상메서드 만들기
//        if (row==1) {
//            System.out.println("수정 성공");
//        }
//
//    }

    public void detailMenu(int no, Model model) {

        MenuDTO menu = menuMapper.getMenu2(no);
        model.addAttribute("menu", menu);

        // 컨트롤러에서 값 받기 방식을 int (param) 값 하나만 받기로 한 순간
        // 자동으로 만들어주는 객체 menuDTO 같은 건 없음
        // 그러니까 온전히 값이 잘 세팅 되어 있는 객체를 attr로 만들어서 결과로 가야하는 것 (${menu})

    }

    public void detailMenu(MenuDTO menuDTO) {

        // 컨트롤러에서 값받기 방식을 '객체'로 선택한 순간 클래스 이름 첫글자를
        // 소문자로 바꾼 형태인 menuDTO라는 이름의 attr를 spring이 자동으로 만들어줬음.
        // 그러니까 넘겨받은 m_no를 제외한 나머지 값만 온전히 set 해주면 끝나는 거.
        // 이미 attr이 있으니까 따로 model에 addAttr 할 필요가 없음.

        MenuDTO menu = menuMapper.getMenu(menuDTO);
        menuDTO.setM_name(menu.getM_name());
        menuDTO.setM_price(menu.getM_price());

    }

    public MenuDTO menuJSON(MenuDTO menuDTO) {
        MenuDTO menu = menuMapper.getMenu(menuDTO);
        menuDTO.setM_name(menu.getM_name());
        menuDTO.setM_price(menu.getM_price());
        return menuDTO;

    }




    public void upMenu(MenuDTO menuDTO) {
      if (menuMapper.upMenu(menuDTO) ==1 ){
          System.out.println("수정성공");
      };
    }

    public void regMenu(MenuDTO menuDTO) {
        if (menuMapper.regMenu(menuDTO) ==1 ){
            System.out.println("등록성공");
        };
}}