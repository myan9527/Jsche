package org.jsche.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsche.common.token.TokenHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ValidTokenInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        if (!TokenHandler.checkToken(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

}
