package com.smhdemo.web;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
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
	private Producer captchaProducer;

	@RequestMapping(value = "/captcha-image", method = RequestMethod.GET)
	public ModelAndView getKaptchaImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String code = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String capText = captchaProducer.createText();
		// 将系统生成的验证码放入Session中
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "accountName", required = true) String accountName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "checkCode", required = true) String checkCode,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			// 校验码验证
			String kaptchaExpected = (String) request.getSession()
					.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			// 进行比较
			if (checkCode.length() == 0
					|| !checkCode.equalsIgnoreCase(kaptchaExpected)) {
				redirectAttributes.addFlashAttribute("message", "验证码输入错误");
				return "redirect:/login.do";
			}
			// 使用权限工具进行用户登录
			SecurityUtils.getSubject().login(
					new UsernamePasswordToken(accountName, password));
			LoginLog vo = new LoginLog();
			vo.setAccountName(accountName);
			vo.setIpAddress(getRemoteIp(request));
			securityFac.addLoginLog(vo);
			return "redirect:/index.do";
		} catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/login.do";
		}
	}

	private String getRemoteIp(HttpServletRequest request) {
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
