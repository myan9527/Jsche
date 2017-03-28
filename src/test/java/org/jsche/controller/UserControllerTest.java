package org.jsche.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsche.common.Constants;
import org.jsche.common.ErrorMessage;
import org.jsche.entity.User;
import org.jsche.service.TaskService;
import org.jsche.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringRunner.class)
public class UserControllerTest extends WebAPIBaseTest<UserController> {

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
    public void testProcessLogin() {
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
        Assert.assertEquals(mav.getModel().get(Constants.ERROR_ATTR_NAME), ErrorMessage.INVALID_PASSWORD);
        when(userService.getUserByEmail(anyString())).thenReturn(null);
        mav = controller.processLogin(request, "email", "123465");
        Assert.assertEquals(mav.getViewName(), "user/login");
        Assert.assertEquals(mav.getModel().get(Constants.ERROR_ATTR_NAME), ErrorMessage.NO_SUCH_USER);
    }

    @Test
    public void testProcessRegister() {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn(null);
        when(user.getPassword()).thenReturn("123");
        ModelAndView mav = controller.processRegister(request, user, "1234");
        Assert.assertEquals(mav.getViewName(), "user/register");
        Assert.assertEquals(mav.getModel().get(Constants.ERROR_ATTR_NAME), ErrorMessage.UNMATCHED_PASSWORD);

        when(user.getEmail()).thenReturn("email");
        when(user.getPassword()).thenReturn("1234");
        when(userService.getUserByEmail("email")).thenReturn(user);
        mav = controller.processRegister(request, user, "1234");
        Assert.assertEquals(mav.getViewName(), "user/register");
        Assert.assertEquals(mav.getModel().get(Constants.ERROR_ATTR_NAME), ErrorMessage.EMAIL_REGISTERED);

        when(userService.getUserByEmail("email")).thenReturn(null);
        mav = controller.processRegister(request, user, "1234");
        Assert.assertEquals(mav.getViewName(), "redirect:/login");
        verify(userService).save(user);
    }

    @Test
    public void testDashboard() {
        User user = mock(User.class);
        Pageable pageable = mock(Pageable.class);
        when(session.getAttribute(Constants.LOGIN_USER)).thenReturn(user);
        when(user.getId()).thenReturn(1);
        ModelAndView mav = controller.dashboard(session, pageable);
        verify(taskService).getUserTasks(1, pageable);
        Assert.assertEquals(mav.getViewName(), "user/dashboard");
    }

    @Test
    public void testProfile() {
        Assert.assertEquals(controller.profile(), "user/profile");
    }

    @Test
    public void testLogout() {
        String result = controller.logout(session);
        verify(session).removeAttribute(Constants.LOGIN_USER);
        Assert.assertEquals(result, "index");
    }

}
