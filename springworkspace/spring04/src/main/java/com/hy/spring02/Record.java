package com.hy.spring02;

import lombok.Data;

@Data
public class Record {

    private String name;
    private double mid;
    private double fin;

    // attr 뭉쳐서 보내기
    private double avg;
    private String grade;

}
