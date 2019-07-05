package com.by.mapper;

import com.by.model.Role;
import com.by.model.User;
import com.by.model.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select * from t_role")
    List<Role> list();
    @Insert("insert into t_role(role_name) values(#{roleName})")
    void add(Role role);
    @Select("select * from t_role where role_id = #{roleId}")
    Role selectById(@Param("roleId") Integer roleId);
    @Update("update t_role set role_name = #{roleName} where role_id = #{roleId}")
    void update(Role role);
    @Delete("delete from t_role where role_id = #{roleId}")
    void deleteById(Integer roleId);


    @Select("select * from t_role")
    List<Role> findAll();
}
