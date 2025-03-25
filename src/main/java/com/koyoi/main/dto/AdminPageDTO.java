package com.koyoi.main.dto;

import java.util.List;

public class AdminPageDTO<T> {

    private List<T> list;
    private int total;

    public AdminPageDTO(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public int getTotal() {
        return total;
    }

}
