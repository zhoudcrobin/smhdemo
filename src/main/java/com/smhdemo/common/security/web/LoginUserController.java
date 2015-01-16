package com.smhdemo.common.security.web;

import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.smhdemo.common.security.SecurityFacable;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.common.security.entity.UserInfo;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@RequestMapping(value = "/common/security/loginuser")
public class LoginUserController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginUserController.class);
	@Autowired
	private SecurityFacable securityFac;
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(){ 
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();   
        return "redirect:/login.do";
    } 
	@RequestMapping(value="/updinfo",method=RequestMethod.GET)
	public String updUserInfo(Model model){
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		try{
			User user = securityFac.getUser(loginName);
			model.addAttribute("userInfo", user.getUserInfo());
		}catch(Exception e){
			logger.debug(e.toString());
		}
		return "common/security/loginuser/updinfo";
	}
	@RequestMapping(value="/updinfo",method=RequestMethod.POST)
	public String updUserInfo(@Valid @ModelAttribute UserInfo userInfo, BindingResult result,Model model){
        if(result.hasErrors()){
            return "common/security/loginuser/updinfo";
        } 
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		try{
			User user = securityFac.getUser(loginName);
			user.setUserInfo(userInfo);
			securityFac.updUser(user);
			model.addAttribute("message", "用户信息修改成功");
		}catch(Exception e){
			logger.debug(e.toString());
		}
		
		return "common/security/loginuser/updinfo";
	}
	
	@RequestMapping(value="/updpassword",method=RequestMethod.GET)
	public String updUserPassword(){
		return "common/security/loginuser/updpassword";
	}
	
	@RequestMapping(value="/updpassword",method=RequestMethod.POST)
	public String updUserPassword(@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "againPassword", required = true) String againPassword,Model model){
		
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		try{
			User user = securityFac.getUser(loginName);
			if(oldPassword.equals(user.getPassword())){
				if(newPassword.equals(againPassword)){
					user.setPassword(newPassword);
					securityFac.updUser(user);
					model.addAttribute("message", "密码修改成功");
				}else{
					model.addAttribute("message", "新密码和确认密码输入不一致");
				}
			}else{
				model.addAttribute("message", "现有密码输入不正确");
			}
		}catch(Exception e){
			logger.debug(e.toString());
		}
		
		return "common/security/loginuser/updpassword";
	}	
	@RequestMapping("/403.do")
	public String unauthorizedRole(){
		return "common/security/loginuser/accessdenied";
	}	
}

