package com.springboot.service;


import com.springboot.entity.VPoetry;

import java.util.List;

public interface VPoetryService {
    List<VPoetry> showAll();

    List<VPoetry> showAllbyKw(String kw);

    List<VPoetry> displayPoetry(Integer id1);
}
