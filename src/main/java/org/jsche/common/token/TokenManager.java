package org.jsche.common.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class TokenManager {
    public static final String TOKEN_ATTR_NAME = "csrf_token";
    private static CsrfToken token;

    public static String getSessionToken(HttpSession session) {
        synchronized (session) {
            String tokenString = (String) session.getAttribute(TOKEN_ATTR_NAME);
            if (tokenString == null || tokenString.length() == 0) {
            	TokenManager.token = CsrfToken.getTempToken();
                session.setAttribute(TOKEN_ATTR_NAME, token);
            }

        }
        return token.getTokenizer();
    }
    
    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getParameter(TOKEN_ATTR_NAME);
    }

	public static CsrfToken getCsrfToken() {
		token = CsrfToken.getTempToken();
		return token;
	}

}
