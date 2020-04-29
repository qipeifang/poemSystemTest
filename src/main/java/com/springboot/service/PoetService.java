package com.springboot.service;

import com.springboot.entity.TPoet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PoetService extends java.io.Serializable {
//    Page<TPoet> findAll(String kw, Pageable pageable);

    void deleteById(long id);

    List<TPoet> showAll(String kw);
}
