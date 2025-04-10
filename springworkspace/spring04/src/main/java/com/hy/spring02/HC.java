package com.hy.spring02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HC {

    // 땡길 게 있어야 함(@Service)
    @Autowired  // 객체 생성해서 집어 넣어주는 거 DI
    private M m;

    @GetMapping("/")
    public String home() {
        return "input";
    }

    @PostMapping("/record-show")
    public String recordShow(Record r) {
        m.calc(r);
        return "output";
    }

}
