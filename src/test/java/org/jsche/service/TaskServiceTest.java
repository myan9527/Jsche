package org.jsche.service;

import java.util.ArrayList;
import java.util.List;

import org.jsche.common.exception.ServiceException;
import org.jsche.entity.Task;
import org.jsche.repo.TaskRepository;
import org.jsche.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository tp;
    private TaskService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new TaskServiceImpl();
        Whitebox.setInternalState(service, "tp", tp);
    }

    @Test
    public void testBuildTypeArray() {
        Assert.assertEquals(service.buildTypeArray().length, 5);
    }

    @Test
    public void testGetUserTasks() {
        List<Task> tasks = new ArrayList<>();
        Pageable pageable = mock(Pageable.class);
        when(tp.getTaskByUserId(anyInt(), anyObject())).thenReturn(tasks);
        Assert.assertEquals(tasks, service.getUserTasks(1, pageable));
    }

    @Test
    public void testSave() {
        Task task = mock(Task.class);
        service.save(task);
        verify(tp, times(1)).save(task);
    }

    @Test(expected = ServiceException.class)
    public void testSaveThrows() {
        Task task = mock(Task.class);
        when(tp.findOne(anyInt())).thenReturn(task);
        service.save(task);
    }

}
