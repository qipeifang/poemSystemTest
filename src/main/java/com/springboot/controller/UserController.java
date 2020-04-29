package com.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Result;
import com.springboot.entity.TCollection;
import com.springboot.entity.TUser;
import com.springboot.entity.UserLogin;
import com.springboot.entity.VCollAndPoem;
import com.springboot.security.SHA1Test;
import com.springboot.service.CollectionService;
import com.springboot.service.UserService;
import com.springboot.service.VCollAndPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
		maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("userLogin",new UserLogin());
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Result login(UserLogin user, BindingResult result,
						HttpSession session) throws NoSuchAlgorithmException {
		Result result2 = new Result();
		if (result.hasErrors()){
			result2.setDescription("登录失败，请重新登录");
			result2.setCode(400);
			result2.setNextAction("/login");
			return result2;
		}
		//检查用户身份的方法
		//把前台传过来的初始密码转换成数据库加密后的密码
		SHA1Test sha1Test = new SHA1Test();
		user.setPassword(sha1Test.toHexString(user.getPassword()));
		//检查用户身份的方法
		TUser u=userService.checkUser(user);
		if(u!=null){//判断用户是否存在
			session.setAttribute("usersession",u);//去数据库中检索用户或修改checkUser返回用户对象
			result2.setDescription("登录成功");
			result2.setCode(200);
			result2.setNextAction("/web/index");
			return result2;
		}
		result2.setDescription("登录失败，请重新登录");
		result2.setNextAction("/login");
		result2.setCode(400);
		return result2;
	}
	//注册跳转
	@RequestMapping("goregister")
	public String goregister() {
		return "register";
	}
	//注册
	@PostMapping("/register")
	@ResponseBody
	public Result register(TUser user, Model mv, HttpSession session)throws NoSuchAlgorithmException {
		Result result2 = new Result();
		/*
		//检查验证码
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			result2.setDescription("验证码错误");
			result2.setCode(400);
			result2.setNextAction("/register");
			return result2;
		}*/
		//检查邮箱是否已注册
		if(userService.findEmail(user.getEmail()).size()!=0){
			result2.setDescription("该邮箱已注册账号，请勿重复注册！");
			result2.setCode(400);
			result2.setNextAction("/login/register");
			return result2;
		}
		else {
			//密码加密
			SHA1Test sha1Test = new SHA1Test();
			user.setPassword(sha1Test.toHexString(user.getPassword()));
			//注册时 一律把isAdmin、isVIP、grade设为0
			user.setIsManager(0);
			user.setIsVIP(0);
			user.setGrade(0);
			userService.addUser(user);
			result2.setDescription("注册成功，请登录");
			result2.setCode(200);
			result2.setNextAction("/login");
			return result2;
		}
	}

	//跳转个人信息管理界面
	@GetMapping("goPersonalInfo")
	public String goPersonalInfo( Model mv, HttpSession session){
		TUser user=(TUser) session.getAttribute("user");
		System.out.println(user.getId());
		mv.addAttribute("user",user);
		mv.addAttribute("sexes",TUser.Sex.toList());
		return "personalInfo";
	}
	//个人信息管理界面中:更改信息后保存
	@PostMapping("SavePersonalInfo")
	public String SavePersonalInfo(Model mv,HttpSession session,TUser user,BindingResult bindingResult) {
		userService.modifyUser(user);
		mv.addAttribute("message","修改个人信息成功！");
		mv.addAttribute("sexes",TUser.Sex.toList());
		mv.addAttribute("user",user);
		return "personalInfo";
	}

}
