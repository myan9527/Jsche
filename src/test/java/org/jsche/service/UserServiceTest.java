package org.jsche.service;

import org.jsche.entity.User;
import org.jsche.repo.UserRepository;
import org.jsche.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository up;
    private UserService service;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        service = new UserServiceImpl();
        Whitebox.setInternalState(service, "up", up);
    }
    
    @Test
    public void testSave(){
        User user = mock(User.class);
        when(user.getAvatar()).thenReturn(null);
        when(user.getEmail()).thenReturn("email");
        service.save(user);
        verify(up).save(user);
    }
    
    @Test
    public void testGetUserById(){
        User user = mock(User.class);
        when(up.findOne(anyInt())).thenReturn(user);
        Assert.assertNotNull(service.getUserById(1));
        Assert.assertEquals(service.getUserById(1), user);
    }
    
    @Test
    public void testGetUserByEmail(){
        User user = mock(User.class);
        when(up.getUserByEmail(anyString())).thenReturn(user);
        Assert.assertNotNull(service.getUserByEmail("email"));
        Assert.assertEquals(service.getUserByEmail("email"), user);
    }
    
    @Test
    public void testUpdateLastLogin(){
        User user = mock(User.class);
        service.updateLastLogin(user);
        verify(up).save(user);
    }
    
    @Test
    public void testUpdateUserAvatar(){
        User user = mock(User.class);
        when(user.getAvatar()).thenReturn(null);
        when(user.getEmail()).thenReturn("email");
        service.updateUserAvatar(user);
        verify(up).save(user);
        
        when(user.getAvatar()).thenReturn("avatar");
        verify(user).setAvatar("URL: http://www.gravatar.com/avatar/0c83f57c786a0b4a39efab23731c7ebc?size=180");
        verify(up).save(user);
    }
}
