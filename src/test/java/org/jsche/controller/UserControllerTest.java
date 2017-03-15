package org.jsche.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.entity.User;
import org.jsche.service.TaskService;
import org.jsche.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringRunner.class)
public class UserControllerTest extends WebAPIBaseTest<UserController>{
    @Mock
    private UserService userService;
    @Mock
    private TaskService taskService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @InjectMocks
    private UserController controller;
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    protected UserController getController() {
        return controller;
    }
    
    @Test
    public void testProcessLogin(){
        User user = mock(User.class);
        when(userService.getUserByEmail(anyString())).thenReturn(user);
        //try to hack it.
        when(request.getSession()).thenReturn(session);
        when(user.getPassword()).thenReturn("81DC9BDB52D04DC20036DBD8313ED055");
        ModelAndView mav = controller.processLogin(request, "email", "1234");
        verify(userService).updateLastLogin(user);
        Assert.assertEquals(mav.getViewName(), "redirect:/user/dashboard");
        
        mav = controller.processLogin(request, "email", "123465");
        Assert.assertEquals(mav.getViewName(), "user/login");
        when(userService.getUserByEmail(anyString())).thenReturn(null);
        mav = controller.processLogin(request, "email", "123465");
        Assert.assertEquals(mav.getViewName(), "user/login");
    }

}
