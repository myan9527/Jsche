package org.jsche.dao;

import org.apache.ibatis.annotations.*;
import org.jsche.entity.User;

/**
 * Created by myan on 2017/3/29.
 */

@Mapper
public interface UserDao {

    @Select("Select * from users where email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Insert("insert into users(name,email,password,avatar) " +
            "values (#{name},#{email},#{password},#{avatar})")
    void save(User user);

    @Select("select * from users where id = #{id}")
    User getUserById(@Param("id") int id);

    @Update("Update users " +
            "set avatar = #{avatar}, customized_avatar = #{customizedAvatar}" +
            "where id = #{id}")
    void updateUserAvatar(User user);

    @Update("Update users " +
            "set last_login = #{lastLogin} " +
            "where id = #{id}")
    void updateLastLogin(User user);
}
