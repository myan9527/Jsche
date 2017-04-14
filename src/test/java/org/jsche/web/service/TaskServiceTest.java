package org.jsche.web.service;

import org.jsche.common.exception.ServiceException;
import org.jsche.web.dao.TaskDao;
import org.jsche.entity.Task;
import org.jsche.web.service.TaskService;
import org.jsche.web.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Test
    public void testGetExtraData() {
    	Assert.assertNull(service.getExtraData(-1));
    	
    	Map<String, Integer> map = new HashMap<>();
    	when(taskDao.getExtraData(anyInt())).thenReturn(map);
    	Assert.assertEquals(map, service.getExtraData(1));
    }
    
    @Test
    public void testGetUserTasksPages() {
    	List<Task> list = new ArrayList<>();
    	when(taskDao.getUserTaskPages(anyObject())).thenReturn(list);
    	Map<String, Object> map = new HashMap<>();
    	Assert.assertEquals(list, service.getUserTasksPages(map));
    }
    
    @Test
    public void testGetUserTaskCount() {
    	List<Task> list = new ArrayList<>();
    	Task task = mock(Task.class);
    	list.add(task);
    	when(taskDao.getTaskByUserId(anyInt())).thenReturn(list);
    	Assert.assertEquals(1, service.getUserTaskCount(1));
    }
    
    @Test
    public void testGetIncomingTasks() {
    	List<Task> list = new ArrayList<>();
    	Task task = mock(Task.class);
    	list.add(task);
    	when(taskDao.getIncomingTasks(anyInt())).thenReturn(list);
    	Assert.assertEquals(list, service.getIncomingTasks(1));
    	
    }

}
