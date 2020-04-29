package com.springboot.dao;

import com.springboot.entity.TPoetryType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoetryTypeDao extends JpaRepository<TPoetryType,Long> {



}
