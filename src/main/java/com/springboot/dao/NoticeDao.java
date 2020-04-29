package com.springboot.dao;

import com.springboot.entity.TNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NoticeDao extends JpaRepository<TNotice,Long> {
    @Modifying
    @Transactional
    TNotice save(TNotice vCollAndPoem);
    //通过邮箱查询通知的信息
    @Query(value = "select * from TNotice u where adminemail=?1 and (content like ?2)",nativeQuery = true)
    Page<TNotice> findByEmail(String email, String kw, Pageable pageable);
    @Query(value = "select * from TNotice u where adminemail=?1 and (content like ?2)",nativeQuery = true)
    List<TNotice> findByEmailAndKw(String email, String kw);
}
