package com.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Result;
import com.springboot.entity.TCollection;
import com.springboot.entity.TComment;
import com.springboot.entity.TUser;
import com.springboot.entity.VCollAndPoem;
import com.springboot.service.CollectionService;
import com.springboot.service.CommentService;
import com.springboot.service.VCollAndPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//收藏管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class CollectionController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    VCollAndPoemService vCollAndPoemService;

    @GetMapping("/listcollections")
    @ResponseBody
    public Result listallcoll(String kw,  Model model,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    @PostMapping("/listcollections")
    @ResponseBody
    public Result listallcollbykw(@RequestBody String kw,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    @GetMapping("/deletecoll")
    @ResponseBody
    public Result delete(@RequestBody Integer id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        collectionService.deleteById(id);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    //批量删除收藏表
    @PostMapping("/deletecolls")
    public String deletes(String ids){
        List<TCollection> colls=new ArrayList<>();
        JSONObject json=JSONObject.parseObject(ids);
        JSONArray arr=json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
        int ilen=arr.size();
        for (int i=0;i<ilen;i++){//每次循环ilen次来执行ilen个查询，再去删除
            colls.add(collectionService.findById(arr.getInteger(i)));
        }
        collectionService.deletes(colls);
        return "redirect:/listcollections";
    }

}
