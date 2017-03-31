/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.common.inteceptor;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.RequiredLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author myan
 */
public class LoginInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            RequiredLogin login = ((HandlerMethod) handler).getMethodAnnotation(RequiredLogin.class);
            if (login == null || !login.value()) {
                // ignore this session check
                return true;
            } else {
                if (request.getSession().getAttribute(Constants.LOGIN_USER) != null) {
                    return true;
                } else {
                    request.setAttribute(Constants.ERROR_ATTR_NAME, ErrorMessage.LOGIN_REQUIRED);
                    response.setDateHeader("expries", -1);
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    if(request.getScheme().equalsIgnoreCase("https")){

                    } else {

                    }
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }
        return true;
    }

}
