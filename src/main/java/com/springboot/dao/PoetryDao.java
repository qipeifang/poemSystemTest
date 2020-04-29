package com.springboot.dao;

import com.springboot.entity.TPoetry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoetryDao extends JpaRepository<TPoetry,Long> {
}
