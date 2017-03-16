package org.jsche.common;

import javax.servlet.http.HttpSession;

import org.jsche.common.token.TokenHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TokenHandlerTest {

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
}
