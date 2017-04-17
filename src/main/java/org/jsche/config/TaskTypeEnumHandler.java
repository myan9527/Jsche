package org.jsche.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.jsche.entity.TaskType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author myan
 * Date 2017/3/30.
 */
public class TaskTypeEnumHandler extends BaseTypeHandler<TaskType> {
    private Class<TaskType> type;

    private final TaskType[] enums;

    public TaskTypeEnumHandler(Class<TaskType> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TaskType taskType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,taskType.ordinal());
    }

    @Override
    public TaskType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        int i = resultSet.getInt(columnName);

        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }

    private TaskType locateTaskType(int i) {
        for (TaskType taskType : enums) {
            if(taskType.ordinal() == i){
                return taskType;
            }
        }
        throw new IllegalArgumentException("Unknown enum type：" + type.getSimpleName());
    }

    @Override
    public TaskType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        int i = resultSet.getInt(columnIndex);

        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }

    @Override
    public TaskType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        int i = callableStatement.getInt(columnIndex);

        if (callableStatement.wasNull()) {
            return null;
        } else {
            return locateTaskType(i);
        }
    }
}
