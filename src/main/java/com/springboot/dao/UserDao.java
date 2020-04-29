package com.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserDao extends JpaRepository<TUser, Long>
, JpaSpecificationExecutor<TUser> {
    @Modifying
    @Transactional
    //保存用户
    TUser save(TUser user);
    //通过email找用户 返回list集合
   // List<TUser> findByEmail(String email);
    Optional<TUser> findByEmail(String email);
    //通过关键字查询用户信息
    @Query("select u from TUser u where username like ?1 or email like ?1")
    public Page<TUser> findByKeyword(String kw, Pageable pageable);

//    void saveOrUpdate(TUser user);

    //Page<TUser> findAll(Pageable pageable);

    //public Page findUserPage(String kw);



}

