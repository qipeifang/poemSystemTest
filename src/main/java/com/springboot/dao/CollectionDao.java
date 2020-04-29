package com.springboot.dao;

import com.springboot.entity.TCollection;
import com.springboot.entity.TUser;
import com.springboot.entity.VCollAndPoem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CollectionDao extends JpaRepository<TCollection, Long> {
    @Modifying
    @Transactional
    //保存收藏
    TCollection save(TCollection colleciton);


}
