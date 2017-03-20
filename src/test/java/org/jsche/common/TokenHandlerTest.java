package org.jsche.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.common.token.TokenHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TokenHandlerTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken() {
        when(session.getAttribute(Constants.TOKEN_ATTR_NAME)).thenReturn(null);
        String token1 = TokenHandler.generateToken(session);
        Assert.assertNotNull(token1);

        when(session.getAttribute(Constants.TOKEN_ATTR_NAME)).thenReturn(new HashMap<>());
        String token2 = TokenHandler.generateToken(session);
        Assert.assertNotNull(token2);
        Assert.assertNotEquals(token1, token2);
    }
    
    @Test
    public void testCheckToken(){
        when(request.getMethod()).thenReturn("GET");
        Assert.assertTrue(TokenHandler.checkToken(request));
        
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(null);
        Assert.assertFalse(TokenHandler.checkToken(request));
        
        Map<String, String[]> params = new HashMap<>();
        params.put(Constants.TOKEN_ATTR_NAME, new String[]{"test"});
        when(request.getParameterMap()).thenReturn(params);
        Map<String, String> tp = new HashMap<>();
        tp.put(Constants.TOKEN_ATTR_NAME + ".test" , "test");
        when(session.getAttribute(Constants.TOKEN_ATTR_NAME )).thenReturn(tp);
        Assert.assertTrue(TokenHandler.checkToken(request));
    }
}
