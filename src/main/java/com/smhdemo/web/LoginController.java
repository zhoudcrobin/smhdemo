package com.smhdemo.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 登录用户的操作处理
 *  
 * @author zhoudongchu
 */
@Controller
public class LoginController {
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value = "accountName", required = true) String accountNmae,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "checkcode", required = false) String checkcode,
			RedirectAttributes redirectAttributes){
		try {
			//使用权限工具进行用户登录
			SecurityUtils.getSubject().login(new UsernamePasswordToken(accountNmae, password));
			return "redirect:/index.do";
		} catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("message","用户名或密码错误");
			return "redirect:/login.do";
		}
	}
}
