package com.example.spring01;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// jsp - controller - servlet
// doget, dopost
// ip주소/프로젝트명/컨트롤러이름(url)

// spring - controller - 클래스 @Controller
// 요청 매핑 - 일반 메소드 @RequestMapping, @GetMapping, @PostMapping
// ip주소/만든url




@Controller
public class HC {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest req, Model model) {
        System.out.println("home 진입");
        req.setAttribute("str","애트리뷰트값");
        model.addAttribute("str2","thymeleaf test~");
        model.addAttribute("a","<a> 링크 </a>");
        model.addAttribute("b","차이점 <b> 확인 </b>");
        model.addAttribute("age","10");



        return "index.html"; // forward
    }


    @GetMapping("/index.go")
    public String indexGo(){
        System.out.println("index go 진입 --");
        return "index";

    }

    // localhost:8080/tt    get 요청이 들어오면 tt 출력

    @GetMapping("/tt")
    public String tt(){
        System.out.println("tt");
        return "index";
    }

    // localhost:8080/index.do  get 요청하면 "index do 요청" 출력

    @GetMapping("/index.do")
    public String indexDo(){
        System.out.println("index do 요청");
        return "redirect:/";
    }

    // localhost:8080/yy        get 요청 들어오면 yy 출력
    @GetMapping("/yy")
    public void yy(){
        System.out.println("yy");
    }

    // localhost:8080/user.info
    // localhost:8080/user-info     // 이걸로 선택해라.  SEO에 더 유리.
    // .은 단어 구분이 아닌 확장자로 해석할 여지 있음.


    // post 요청 처리 받으면 "user info 진입" 출력

    @PostMapping("/user-info")
    public String userInfo(){
        System.out.println("user info 진입");
        return "index.html";
    }

}