package com.smhdemo.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/index.do")
	public String forwordHome(){
		return "home";
	}
}

