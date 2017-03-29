package org.jsche.service;

import org.jsche.common.exception.ServiceException;
import org.jsche.dao.TaskDao;
import org.jsche.entity.Task;
import org.jsche.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskDao taskDao;
    private TaskService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new TaskServiceImpl();
        Whitebox.setInternalState(service, "taskDao", taskDao);
    }

    @Test
    public void testBuildTypeArray() {
        Assert.assertEquals(service.buildTypeArray().length, 5);
    }

    @Test
    public void testGetUserTasks() {
        List<Task> tasks = new ArrayList<>();
//        Pageable pageable = mock(Pageable.class);
        when(taskDao.getTaskByUserId(anyInt())).thenReturn(tasks);
        Assert.assertEquals(tasks, service.getUserTasks(1));
    }

    @Test
    public void testSave() {
        Task task = mock(Task.class);
        service.save(task);
        verify(taskDao, times(1)).save(task);
    }

    @Test(expected = ServiceException.class)
    public void testSaveThrows() {
        Task task = mock(Task.class);
        when(taskDao.getTaskById(anyInt())).thenReturn(task);
        service.save(task);
    }

}
