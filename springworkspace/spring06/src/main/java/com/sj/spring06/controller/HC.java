package com.sj.spring06.controller;

import com.sj.spring06.service.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HC {

@Autowired
private M m;

@GetMapping ("/")
    public String home() {
    return "index";


    //1. 프로젝트 생성 (spring boot)
    //- 6개 starter
    //lombok, conf pross, devtools,
    //spring web,
    //mybatis, oracle driver
    //
    //2. mvc 설정
    //C - @Controller
    //M - @Service
    //V -
    //1) jsp 설정    gradle - dependencies.. jar
    //application.properties - 설정 추가 (main에다가 webapp/WEB-INF/views 디렉토리 추가)
    //
    //3. DB 설정
    //- cloud 유저는 jar 3개(cert,core,pki)
    //application.properties - db서버 설정, batis 설정 (resources아래에 mappers 폴더 안에 xml들)
    //
    //4. db 사용법
    //xml만들었으면 namespace에 interface 적어주기
    //interface   mapper.xml과 대응되는 이름으로 생성하고
    //거기에 @Mapper 적어주기
    //그럼 M 이 interface를 @Autowired로 당겨 쓸 수 있음.
}
}
