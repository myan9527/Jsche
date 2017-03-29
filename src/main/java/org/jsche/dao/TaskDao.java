package org.jsche.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jsche.entity.Task;

import java.util.List;

/**
 * Created by myan on 2017/3/29.
 */
@Mapper
public interface TaskDao {
    @Select("Select * from tasks where user_id = #{userId}")
    List<Task> getTaskByUserId(@Param("userId") int userId);

    @Select("Select * from tasks where id = #{id}")
    Task getTaskById(@Param("id") int id);

    @Insert("insert into tasks(task_name,task_type,user_id,description,duration,priority,start_date) " +
            "values (#{taskName},#{taskType},#{userId},#{description},#{duration},#{priority},#{start_date})")
    void save(Task task);

    @Select("SELECT * FROM tasks t where t.user_id = #{userId} and t.start_date <= now() + 3")
    List<Task> getIncomingTasks(@Param("userId") int userId);
}
