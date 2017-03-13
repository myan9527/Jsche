/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.jsche.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author myan
 */
public class LoginInteceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		HandlerMethod methodHandler = (HandlerMethod) handler;
		RequiredLogin login = methodHandler.getMethodAnnotation(RequiredLogin.class);
		if(login != null && !login.value())
			//ignore this session check
			return true;
		User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
		if(user == null){
			request.setAttribute(Constants.ERROR_ATTR_NAME, ErrorMessage.LOGIN_REQUIRED);
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}

}
