package com.springboot.dao;

import com.springboot.entity.TComment;
import com.springboot.entity.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentDao extends JpaRepository<TComment, Long> {
    @Modifying
    @Transactional
        //保存评论
    TComment save(TComment comment);
    Optional<TComment> findByEmail(String email);
    //通过关键字查询评论信息
//    @Query("select c from TComment c where email like ?1 or comments like ?1 or poetryid like ?1")
//    public Page<TComment> findByKeyword(String kw, Pageable pageable);

   // Page<TComment> findAll(Pageable pageable);

    //通过邮箱查询用户评论的信息
    @Query(value = "select * from TComment u where email=?1 and (comments like ?2 or poetryname like ?2)",nativeQuery = true)
    Page<TComment> findByEmail(String email, String kw, Pageable pageable);
    @Query(value = "select * from TComment u where email=?1 and (comments like ?2 or poetryname like ?2)",nativeQuery = true)
    List<TComment> findByEmailAndKw(String email, String kw);
}
