package com.smhdemo.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhdemo.common.security.SecurityFac;
import com.smhdemo.common.security.entity.LoginLog;

/**
 * 登录用户的操作处理
 * 
 * @author zhoudongchu
 */
@Controller
public class LoginController {
	@Autowired
	private SecurityFac securityFac;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "accountName", required = true) String accountName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "checkcode", required = false) String checkcode,
			RedirectAttributes redirectAttributes) {
		try {
			// 使用权限工具进行用户登录
			SecurityUtils.getSubject().login(
					new UsernamePasswordToken(accountName, password));
			LoginLog vo = new LoginLog();
			vo.setAccountName(accountName);
			vo.setIpAddress(getRemoteIp());
			securityFac.addLoginLog(vo);
			return "redirect:/index.do";
		} catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/login.do";
		}
	}

	private String getRemoteIp() {
		String remoteIp;

		remoteIp = request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("X-Real-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("HTTP_CLIENT_IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getRemoteAddr();
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getRemoteHost();
		}

		return remoteIp;
	}
}
