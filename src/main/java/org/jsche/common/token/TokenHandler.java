package org.jsche.common.token;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.common.Constants;

public class TokenHandler {

    static Map<String, String> tokenHolder = null;

    @SuppressWarnings("unchecked")
    public static String generateToken(HttpSession session) {
        String token = "";
        synchronized (session) {
            Object obj = session.getAttribute(Constants.TOKEN_ATTR_NAME);
            if (obj != null)
                tokenHolder = (Map<String, String>) session.getAttribute(Constants.TOKEN_ATTR_NAME);
            else
                tokenHolder = new HashMap<String, String>();
            token = new BigInteger(165, new Random()).toString(36).toUpperCase();
            tokenHolder.put(Constants.TOKEN_ATTR_NAME + "." + token, token);
            session.setAttribute(Constants.TOKEN_ATTR_NAME, tokenHolder);
            Constants.TOKEN_VALUE = token;
        }
        return token;
    }

    @SuppressWarnings("unchecked")
    public static boolean checkToken(HttpServletRequest request) {
        String formToken = getFormToken(request);
        if(formToken == null && request.getMethod().equals("GET")){
            return true;
        }else{
            //intercept each post request.
            HttpSession session = request.getSession();
            Map<String, String> tokenMap = (Map<String, String>) session.getAttribute(Constants.TOKEN_ATTR_NAME);
            if (tokenMap == null || tokenMap.size() < 1)
                return false;
            String sessionToken = tokenMap.get(Constants.TOKEN_ATTR_NAME + "." + formToken);
            if (formToken!=null && !formToken.equals(sessionToken))
                return false;
            tokenMap.remove(Constants.TOKEN_ATTR_NAME + "." + formToken);
            session.setAttribute(Constants.TOKEN_ATTR_NAME, tokenMap);
            return true;
        }
    }

    private static String getFormToken(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (!params.containsKey(Constants.TOKEN_ATTR_NAME))
            return null;
        String[] tokens = (String[]) (String[]) params.get(Constants.TOKEN_ATTR_NAME);
        if ((tokens == null) || (tokens.length < 1))
            return null;
        return tokens[0];
    }
}
