package com.by.mapper;

import com.by.model.Permission;
import com.by.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PermissionMapper {
    @Select("select * from t_permission")
    List<Permission> findAll();
    @Insert(" insert into t_permission(permission_name) values(#{permissionName})")
    void add(Permission permission);
    @Select("select * from t_permission where permission_id = #{permissionId}")
    Permission selectById(@Param("permissionId") Integer permissionId);
    @Update("update t_permission set permission_name = #{permissionName} where permission_id =#{permissionId}")
    void update(Permission permission);
    @Delete("delete from t_permission where permission_id = #{permissionId}")
    void deleteById(Integer permissionId);
}
