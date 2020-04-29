package com.springboot.service;

import com.springboot.entity.TComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService extends java.io.Serializable {

//    void addComment(TComment comment);
//    void modifyComment(TComment comment);
//    List<TComment> findEmail(String email);
//    //管理员对评论的操作
//    public void save(TComment c)throws Exception;
//    public Page<TComment> findAll(String kw, Pageable pageable);
//    public TComment findById(long id);
//    public void deletecomment(TComment c);//删除一条信息
//    public boolean deletecommentById(long id);
//    public boolean deletecomments(List<TComment> comments);//批量删除
//
//    //分页查询
//    Page<TComment> findBookNoCriteria(Integer page, Integer size);
//    //关键字分页查询
//    Page<TComment> findBookCriteria(Integer page, Integer size,String kw);


    Page<TComment> findAll(String email, String kw, Pageable pageable);

    void deleteById(long id);

    List<TComment> showAll(String email, String kw);
}
