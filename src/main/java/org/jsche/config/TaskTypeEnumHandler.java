package org.jsche.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.jsche.entity.Task;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by myan on 2017/3/30.
 */
public class TaskTypeEnumHandler extends BaseTypeHandler<Task.TaskType> {
    private Class<Task.TaskType> type;

    private final Task.TaskType[] enums;

    public TaskTypeEnumHandler(Class<Task.TaskType> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Task.TaskType taskType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,taskType.ordinal());
    }

    @Override
    public Task.TaskType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        int i = resultSet.getInt(columnName);

        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }

    private Task.TaskType locateTaskType(int i) {
        for (Task.TaskType taskType : enums) {
            if(taskType.ordinal() == i){
                return taskType;
            }
        }
        throw new IllegalArgumentException("Unknown enum type：" + type.getSimpleName());
    }

    @Override
    public Task.TaskType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        int i = resultSet.getInt(columnIndex);

        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }

    @Override
    public Task.TaskType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        int i = callableStatement.getInt(columnIndex);

        if (callableStatement.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }
}
