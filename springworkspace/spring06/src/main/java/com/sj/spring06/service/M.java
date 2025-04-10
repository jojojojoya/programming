package com.sj.spring06.service;

import com.sj.spring06.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class M {

    @Autowired
    private ProductMapper mapper;



}
