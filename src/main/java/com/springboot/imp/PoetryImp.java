package com.springboot.imp;

import com.springboot.dao.PoetryDao;
import com.springboot.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PoetryImp implements PoetryService {
    @Autowired
    private PoetryDao poetryDao;
}
