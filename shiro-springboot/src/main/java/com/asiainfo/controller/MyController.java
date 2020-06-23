package com.asiainfo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MyController
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-23 22:06
 **/
@Controller
public class MyController {

	@RequestMapping({"/" ,"/index"})
	public String toIndex(Model model){
		model.addAttribute("msg","hello shiro");
		return "index";
	}

	@RequestMapping("/user/add")
	public String add(){
		return "user/add";
	}

	@RequestMapping("/user/update")
	public String update(){
		return "user/update";
	}

	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}

	@RequestMapping("/noAuth")
	public String noAuth(){
		return "noAuth";
	}

	/**
	 * 登录逻辑处理
	 */
	@RequestMapping("/login")
	public String login(String name,String password,Model model){
		System.out.println("name="+name);
		/**
		 * 使用Shiro编写认证操作
		 */
		//1.获取当前的用户Subject
		Subject subject = SecurityUtils.getSubject();

		//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);

		//3.执行登录方法
		try {
			subject.login(token);

			//登录成功
			//跳转到test.html
//			return "redirect:/testThymeleaf";
			return "index";
		} catch (UnknownAccountException e) {
			//e.printStackTrace();
			//登录失败:用户名不存在，UnknownAccountException是Shiro抛出的找不到用户异常
			model.addAttribute("msg", "用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//e.printStackTrace();
			//登录失败:密码错误，IncorrectCredentialsException是Shiro抛出的密码错误异常
			model.addAttribute("msg", "密码错误");
			return "login";
		}
	}
}
