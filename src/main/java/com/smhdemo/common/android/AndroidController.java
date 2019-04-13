package com.smhdemo.common.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Controller
@ResponseBody
@RequestMapping(value = "/common/android")
public class AndroidController {
	@RequestMapping(value = "/user")
	public Object getUserInfo(){
		List<UserInfo> userList = new ArrayList<UserInfo>();
		UserInfo user1 = new UserInfo();
		user1.setAge(18);
		user1.setName("王明");
		user1.setSex("男");
		userList.add(user1);
		
		UserInfo user2 = new UserInfo();
		user2.setAge(26);
		user2.setName("小草");
		user2.setSex("女");
		userList.add(user2);	
		
		UserInfo user3 = new UserInfo();
		user3.setAge(45);
		user3.setName("张山");
		user3.setSex("男");
		userList.add(user3);			
		return userList;
	}
}

