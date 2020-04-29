package com.springboot.dao;

import com.springboot.entity.TPoet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PoetDao extends JpaRepository<TPoet,Long> {
    @Modifying
    @Transactional
    TPoet save(TPoet vCollAndPoem);
    //通过关键字查询诗人的信息
    @Query(value = "select * from TPoet u where dynasty=?1 or poetname like ?1",nativeQuery = true)
    List<TPoet> findBykw(String kw);
}
