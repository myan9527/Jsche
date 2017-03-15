package org.jsche.repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.jsche.entity.User;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository up;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetUserByEmail(){
        User user = mock(User.class);
        when(up.getUserByEmail(anyString())).thenReturn(user);
        Assert.assertNotNull(up.getUserByEmail("email"));
    }
}
