package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TNotice;
import com.springboot.entity.TUser;
import com.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//通知管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/listnotics")
    @ResponseBody
    public Result listallcoll(String kw, Model model, HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示通知
        List<TNotice> listnots= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listnots);
        return result;
    }
    @PostMapping("/listnotics")
    @ResponseBody
    public Result listallcollbykw(@RequestBody String kw,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示通知
        List<TNotice> listnots= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listnots);
        return result;
    }
    @GetMapping("/deletenotic")
    @ResponseBody
    public Result delete(@RequestBody Integer id, HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        noticeService.deleteById(id);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示通知
        List<TNotice> listnots= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listnots);
        return result;
    }
}
