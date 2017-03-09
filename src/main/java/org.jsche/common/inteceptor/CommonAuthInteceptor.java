/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice.common.inteceptor;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import practice.common.Constant;
import practice.common.annotation.LoginRequired;

/**
 *
 * @author myan
 */
public class CommonAuthInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            LoginRequired login = ((HandlerMethod)handler).getMethodAnnotation(LoginRequired.class);
            if(login == null || login.validate() == false){
                return true;
            }else{
                if(req.getSession().getAttribute(Constant.LOGIN_TOKEN) != null){
                    return true;
                }else{
                    resp.setDateHeader("expries", -1);
                    resp.setHeader("Cache-Control", "no-cache");
                    resp.setHeader("Pragma", "no-cache");
                    
                    StringBuffer path = req.getRequestURL();
                    String params = req.getQueryString();
                    if(!StringUtils.isEmpty(params)){
                        path.append("?")
                            .append(params);
                    }
                    StringBuilder baseUrl = new StringBuilder(req.getContextPath());
                    baseUrl.append("/user/login");
                    if(!StringUtils.isEmpty(path.toString())){
                        baseUrl.append("?callback=")
                                .append(URLEncoder.encode(path.toString(), "UTF-8"))
                                .append("&s=")
                                .append(Math.random());
                    }
                    resp.sendRedirect(baseUrl.toString());
                    return false;
                }
            }
        }else{
            return true;
        }
    }

}
