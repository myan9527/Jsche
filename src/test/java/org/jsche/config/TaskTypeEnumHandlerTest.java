package org.jsche.config;

import org.apache.ibatis.type.JdbcType;
import org.jsche.entity.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Author myan
 * Date 2017/3/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskTypeEnumHandlerTest {
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private CallableStatement callableStatement;

    private TaskTypeEnumHandler handler;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        handler = new TaskTypeEnumHandler(Task.TaskType.class);
    }

    @Test
    public void testSetNonNullParameter() throws SQLException {
        Task.TaskType typo = Task.TaskType.FAMILY_ISSUE;
        handler.setNonNullParameter(statement,1, typo,JdbcType.INTEGER);
        verify(statement).setInt(1,typo.ordinal());
    }

    @Test
    public void testGetNullableResult1() throws SQLException {
        when(resultSet.getInt(anyString())).thenReturn(1);
        Task.TaskType typo = handler.getNullableResult(resultSet, "task_type");
        assertEquals(Task.TaskType.SELF_IMPROVEMENT, typo);

        when(resultSet.wasNull()).thenReturn(true);
        typo = handler.getNullableResult(resultSet,"task_type");
        assertNull(typo);
    }

    @Test
    public void testGetNullableResult2() throws SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(1);
        Task.TaskType typo = handler.getNullableResult(resultSet, 1);
        assertEquals(Task.TaskType.SELF_IMPROVEMENT, typo);

        when(resultSet.wasNull()).thenReturn(true);
        typo = handler.getNullableResult(resultSet, 1);
        assertNull(typo);
    }

    @Test
    public void testGetNullableResult3() throws SQLException {
        when(callableStatement.getInt(anyInt())).thenReturn(1);
        Task.TaskType typo = handler.getNullableResult(callableStatement,1);
        assertEquals(Task.TaskType.SELF_IMPROVEMENT, typo);

        when(callableStatement.wasNull()).thenReturn(true);
        typo = handler.getNullableResult(callableStatement,1);
        assertNull(typo);
    }
}
