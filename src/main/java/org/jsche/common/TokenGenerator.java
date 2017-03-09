package org.jsche.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class TokenGenerator {
    private static final String TOKEN_PREFIX = "0xtoken";
    public static final String TOKEN_ATTR_NAME = "csrf_token";
    private volatile boolean valid = true;

    public static String getSessionToken(HttpSession session) {
        String token;
        synchronized (session) {
            token = (String) session.getAttribute(TOKEN_ATTR_NAME);
            if (token == null || token.length() == 0) {
                token = TOKEN_PREFIX + UUID.randomUUID().toString();
                session.setAttribute(TOKEN_ATTR_NAME, token);
            }

        }
        return token;
    }
    
    public static String getTempToken(){
        return TOKEN_PREFIX + UUID.randomUUID().toString();
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getParameter(TOKEN_ATTR_NAME);
    }

    public void expires() {
        this.setValid(false);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
