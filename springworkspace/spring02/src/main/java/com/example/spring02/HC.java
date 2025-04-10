package com.example.spring02;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HC {

    @GetMapping("/")
    public String home(){
        System.out.println("home===");
        return "input";
    }
// 값 받기 (방법이 여러가지)
    @GetMapping("/add")
    public String add(HttpServletRequest request){

        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        System.out.println(a+b);
        return "output";
    }

    //spring

//    @GetMapping("/add2")
//    public String add2(@RequestParam("a") int a, @RequestParam("b") int b) {
//        System.out.println(a+b);
//        return "output";
//    }

    @GetMapping("/add2")
    public String add2(int a,  int b) {
        System.out.println(a + b);
        return "output";
    }
        //3. 객체 형태


        @GetMapping("/calc")
        public String calc(DataVO dataVO) {
        session.setA("user",dataVO);
            System.out.println(dataVO.getX());
            System.out.println(dataVO.getY());
            return "output";

        }
}
