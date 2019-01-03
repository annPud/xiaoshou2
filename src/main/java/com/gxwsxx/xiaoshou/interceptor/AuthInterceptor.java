package com.gxwsxx.xiaoshou.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.model.UserModel;
import com.gxwsxx.xiaoshou.service.UserService;

public class AuthInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService us;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("method:" + request.getMethod() + "  path:" + request.getRequestURI());
		log.debug("token:" + request.getHeader("token"));
		String token = request.getHeader("token");
		if (null == token) {
			throw new XiaoshouException(XiaoshouException.Type.TOKEN_NOT_FIND);
		}
		UserModel u = us.getUserByToken(token);
		if (null == u) {
			throw new XiaoshouException(XiaoshouException.Type.TOKEN_NOT_FIND);
		}
		return true;
	}

	
}
