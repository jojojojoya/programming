package com.sj.spring05_second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuC {

    @Autowired
    MenuDAO menuDAO;

    @GetMapping("menu-show")
    public String menuShow(Model model) {

        // 전체 조회하는 일
        menuDAO.showAllMenu(model);

        return "select";
    }

    // spring -> 형변환 자동
    // 객체

    @GetMapping("menu-del")
    public String menuDel(int no) { // (@RequestParam("no") int no), 이름이 같아서 생략 가능
        // 삭제하는 일
        menuDAO.deleteMenu(no);
        return "redirect:menu-show";
    }

    @GetMapping("menu-modi")
    public String menuuUpdate(MenuDTO menuDTO) {
        // 수정하는 일
        if (menuDTO.getM_name() == null)
        menuDAO.upMenu(menuDTO);
        return "redirect:menu-show";
    }

/*    @GetMapping("menu-detail")
    public String menuDetail(int no, Model model) {
        menuDAO.detailMenu(no, model);
        return "detail";
    }   */

    // 값, 객체 형태로
    @GetMapping("menu-detail")
    public String menuDetail(MenuDTO menuDTO) {
        menuDAO.detailMenu(menuDTO);
        return "detail";
    }

    @GetMapping("menu-update")
    public String menuUpdateJSP(MenuDTO menuDTO, Model model) {
        menuDAO.detailMenu(menuDTO);
        menuDAO.detailMenu(menuDTO.getM_no(), model);
        return "update";
    }
    //값 받기 - 객체
    @PostMapping("menu-update")
    public String menuUpdate(MenuDTO menuDTO) {
        menuDAO.upMenu(menuDTO);
        return "redirect:menu-show";

    }


    @GetMapping("menu-reg")
    public String menuReg() {
        return "reg";
    }

    @PostMapping("menu-reg")
    public String menuReg(MenuDTO menuDTO) {
        // 전체 조회하는 일
        menuDAO.regMenu( menuDTO);
        return "redirect:menu-show";
    }


    @ResponseBody
    @GetMapping("menu-json")
    public  MenuDTO menuJSON(MenuDTO menuDTO) {
        System.out.println("json 영역 진입----");

        return menuDAO.menuJSON(menuDTO);
    }

    }


