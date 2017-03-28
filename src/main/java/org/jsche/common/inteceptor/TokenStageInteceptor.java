package org.jsche.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsche.common.Constants;
import org.jsche.common.token.TokenHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Set a token for each request.
 *
 * @author myan
 */
public class TokenStageInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute(Constants.TOKEN_ATTR_NAME, TokenHandler.generateToken(request.getSession()));
        return true;
    }

}
