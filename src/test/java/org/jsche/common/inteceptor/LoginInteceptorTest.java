package org.jsche.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoginInteceptorTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse Response;
    @Mock
    private HttpSession session;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    
}
