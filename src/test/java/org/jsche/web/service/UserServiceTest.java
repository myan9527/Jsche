package org.jsche.web.service;

import org.jsche.common.exception.ServiceException;
import org.jsche.web.dao.UserDao;
import org.jsche.entity.User;
import org.jsche.web.service.UserService;
import org.jsche.web.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;
    private UserService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UserServiceImpl();
        Whitebox.setInternalState(service, "userDao", userDao);
    }

    @Test
    public void testSave() {
        User user = mock(User.class);
        when(user.getAvatar()).thenReturn(null);
        when(user.getEmail()).thenReturn("email");
        service.save(user);
        verify(userDao).save(user);
    }

    @Test
    public void testGetUserById() {
        User user = mock(User.class);
        when(userDao.getUserById(anyInt())).thenReturn(user);
        Assert.assertNotNull(service.getUserById(1));
        Assert.assertEquals(service.getUserById(1), user);
    }

    @Test
    public void testGetUserByEmail() {
        User user = mock(User.class);
        when(userDao.getUserByEmail(anyString())).thenReturn(user);
        Assert.assertNotNull(service.getUserByEmail("email"));
        Assert.assertEquals(service.getUserByEmail("email"), user);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateLastLoginThrows() {
        User user = mock(User.class);
        service.updateLastLogin(user);
    }

    @Test
    public void testUpdateLastLogin() {
        User user = mock(User.class);
        when(userDao.getUserById(anyInt())).thenReturn(user);
        service.updateLastLogin(user);
        verify(userDao, times(1)).updateLastLogin(user);
    }

    @Test
    public void testUpdateUserAvatar() {
        User user = mock(User.class);
        when(user.getAvatar()).thenReturn(null);
        when(user.getEmail()).thenReturn("email");
        service.updateUserAvatar(user);
        verify(userDao).updateUserAvatar(user);

        when(user.getAvatar()).thenReturn("avatar");
        when(user.isCustomizedAvatar()).thenReturn(true);
        service.updateUserAvatar(user);
        verify(user).setAvatar("avatar");
        verify(userDao, times(2)).updateUserAvatar(user);
    }
}
