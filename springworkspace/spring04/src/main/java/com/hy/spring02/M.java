package com.hy.spring02;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

// autowired 땡길 수 있게
@Service
public class M {

    public void calc(Record r) {

        double avg = (r.getMid() + r.getFin()) / 2;
        String avg2 = String.format("%.1f", avg);
        String grade = "D";

        if (avg >= 90){
            grade = "A";
        } else if (avg >= 80) {
            grade = "B";
        } else if (avg >= 70) {
            grade = "C";
        }

        // bean에다가 넣어서 불러오면 되니까 model이 필요 없음.
        r.setAvg(avg);
        r.setGrade(grade);

        // 결과 페이지에서 사용하기. attr
/*
        model.addAttribute("avg2", avg2);
        model.addAttribute("grade", grade);
*/

    }
}
