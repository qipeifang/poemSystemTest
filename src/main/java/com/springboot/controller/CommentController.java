package com.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Result;
import com.springboot.entity.TComment;
import com.springboot.entity.TUser;
import com.springboot.security.SHA1Test;
import com.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//评论管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class CommentController {
    @Autowired
    private CommentService commentService;


//    @GetMapping("/listcomments")
//    public String listcomment(String kw, Model model, Pageable pageable){
//        model.addAttribute("kw",kw);
//        if (kw!=null) kw="%"+kw+"%";
//        if (kw==null) kw="%%";
//        Page<TComment> ppu=commentService.findAll(kw, pageable);//默认分页从0页面（第一页），取每页20条数据
//        model.addAttribute("pages",ppu);
//        return "listcomments";//返回界面
//    }
//    @PostMapping("/listcomments")
//    public String listcommentbykw(String kw, Model model, Pageable pageable){
//        model.addAttribute("kw",kw);
//        if (kw!=null) kw="%"+kw+"%";
//        if (kw==null) kw="%%";
//        Page<TComment> ppu=commentService.findAll(kw, pageable);//默认分页从0页面（第一页），取每页20条数据
//        model.addAttribute("pages",ppu);
//        return "listcomments";//返回界面
//    }
//
//    //通过id删除评论
//    @GetMapping("/deletecomment/{id}")
//    public String deletecomment(@PathVariable("id")Integer id){
//        commentService.deletecommentById(id);
//        return "redirect:/listcomments";
//    }
//
//    //批量删除评论
//    @PostMapping("/deletecomments")
//    public String deletecomments(String ids){
//        System.out.println("======"+ids);
//        List<TComment> comments=new ArrayList<>();
//        JSONObject json=JSONObject.parseObject(ids);
//        JSONArray arr=json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
//        int ilen=arr.size();
//        for (int i=0;i<ilen;i++){//每次循环ilen次来执行ilen个查询，再去删除
//            comments.add(commentService.findById(arr.getInteger(i)));
//        }
//        commentService.deletecomments(comments);
//        return "redirect:/listcomments";
//    }

//
//
//    @RequestMapping("/listcomments")
//    public Result listuserbykw(String kw, ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
//                               @RequestParam(value = "size", defaultValue = "5") Integer size) {
//        Result result=new Result();
//        modelMap.addAttribute("kw", kw);
//        if (kw != null) kw = "%" + kw + "%";
//        if (kw == null) kw = "%%";
//        Page<TComment> datas = commentService.findBookCriteria(page, size, kw);//默认分页从0页面（第一页），取每页20条数据
//        modelMap.addAttribute("datas", datas);
//
//        result.setDescription("查询成功");
//        result.setCode(200);
//        result.setData(datas);
//        result.setNextAction("/listcomments");
//        return result;
////        return datas;
//    }
//
//
//    //    @RequestMapping
//    @GetMapping("/deletecomment/{id}")
//    public Result delete(@PathVariable("id") Integer id) {
//        Result result=new Result();
//        //        userService.deleteById(id);
//        if (commentService.deletecommentById(id)) {
//            result.setDescription("删除失败");
//            result.setCode(400);
//            result.setNextAction("/listcomments");
//            return result;
//        }
//        else {
//            result.setDescription("删除成功");
//            result.setCode(200);
//            result.setData(id);
//            result.setNextAction("/listcomments");
//            return result;
//        }
////        return "redirect:/listusers";
//    }
//
//    @PostMapping("/deletecomments")
//    public Result deletes(String ids) {
//        Result result=new Result();
//        System.out.println("======" + ids);
//        List<TComment> comments = new ArrayList<>();
//        JSONObject json = JSONObject.parseObject(ids);
//        JSONArray arr = json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
//        int ilen = arr.size();
//        for (int i = 0; i < ilen; i++) {//每次循环ilen次来执行ilen个查询，再去删除
//            comments.add(commentService.findById(arr.getInteger(i)));
//        }
//        if (commentService.deletecomments(comments)) {
//
//            result.setDescription("删除失败");
//            result.setCode(400);
//            result.setNextAction("/listcomments");
//            return result;
//        }
//        else {
//            result.setDescription("删除成功");
//            result.setCode(200);
//            result.setData(ids);
//            result.setNextAction("/listcomments");
//            return result;
//        }
//    }


    @GetMapping("/listcomments")
    @ResponseBody
    public Result listallcomment(String kw, Model model, HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/listcomments")
    @ResponseBody
    public Result listallcommentbykw(@RequestBody String kw,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/deletecomment")
    @ResponseBody
    public Result delete(@RequestBody String id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        commentService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    //批量删除收藏表
//    @PostMapping("/deletecomments")
////    public String deletes(String ids){
////        List<TComment> colls=new ArrayList<>();
////        JSONObject json=JSONObject.parseObject(ids);
////        JSONArray arr=json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
////        int ilen=arr.size();
////        for (int i=0;i<ilen;i++){//每次循环ilen次来执行ilen个查询，再去删除
////            colls.add(commentService.findById(arr.getInteger(i)));
////        }
////        commentService.deletes(colls);
////        return "redirect:/listcollections";
////    }
}
