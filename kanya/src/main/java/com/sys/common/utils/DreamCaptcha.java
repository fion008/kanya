package com.sys.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import com.sys.base.tool.StringUtil;


/**
 * 如梦验证码
 * @author L.cm
 *
 */
public class DreamCaptcha implements InitializingBean {
	private final static Logger logger = LogManager.getLogger(DreamCaptcha.class);
	private static final String DEFAULT_COOKIE_NAME = "code";
	private final static String DEFAULT_CHACHE_NAME = "dreamCaptchaCache";
	private final static int DEFAULT_MAX_AGE = -1; // cookie超时默认为session会话状态
	
	private CacheManager cacheManager;
	private String cacheName;
	private String cookieName;
	
	
	public DreamCaptcha() {
		this.cacheName = DEFAULT_CHACHE_NAME;
		this.cookieName = DEFAULT_COOKIE_NAME;
	}
	
	public DreamCaptcha(CacheManager cacheManager) {
		this();
		this.cacheManager = cacheManager;
	}
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public String getCacheName() {
		return cacheName;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cacheManager, "cacheManager must not be null!");
		Assert.hasText(cacheName, "cacheName must not be empty!");
		Assert.hasText(cookieName, "cookieName must not be empty!");
	}
	
	/**
	 * 生成验证码
	 */
	public void generate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		// 先检查cookie的uuid是否存在
		String cookieValue = StringUtil.get32UUID();
		String captchaCode = CaptchaUtils.generateCode().toUpperCase();// 转成大写重要
		// 不存在cookie时设置cookie
		WebUtils.setCookie(response, cookieName, cookieValue, DEFAULT_MAX_AGE);
		// 生成验证码
		CaptchaUtils.generate(response, captchaCode);
		
		session.setAttribute("code", captchaCode);
	}
	
	/**
	 * 仅能验证一次，验证后立即删除
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param userInputCaptcha 用户输入的验证码
	 * @return 验证通过返回 true, 否则返回 false
	 */
	public boolean validate(HttpServletRequest request, HttpServletResponse response, String userInputCaptcha) {
		if (logger.isDebugEnabled()) {
			logger.debug("validate captcha userInputCaptcha is " + userInputCaptcha);
		}
		String cookieValue = WebUtils.getCookieValue(request, cookieName);
		if (StringUtils.isBlank(cookieValue)) {
			return false;
		}
		// 转成大写重要
		userInputCaptcha = userInputCaptcha.toUpperCase();
		boolean result = userInputCaptcha.equals(cookieValue);
		if (result) {
			WebUtils.removeCookie(response, cookieName);
		}
		return result;
	}
}
