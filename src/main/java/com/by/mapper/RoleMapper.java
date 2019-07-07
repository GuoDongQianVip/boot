package com.by.mapper;

import com.by.model.Role;
import com.by.model.User;
import com.by.model.UserRole;
import com.by.vo.RolePermissionVo;
import com.by.vo.RoleVo;
import com.by.vo.User1Vo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
   /* @Select("select * from t_role")
    List<Role> list();*/
    @Insert("insert into t_role(role_name) values(#{roleName})")
    void add(String roleName);
    @Select("select * from t_role where role_id = #{roleId}")
    Role selectById(@Param("roleId") Integer roleId);
    @Update("update t_role set role_name = #{roleName} where role_id = #{roleId}")
    void update(Role role);
    @Delete("delete from t_role where role_id = #{roleId}")
    void deleteById(Integer roleId);


    @Select("select * from t_role")
    List<Role> findAll();

    List<RoleVo> select(Map<String, Object> mappage);
    @Select("select permission_id from t_rp where role_id = #{roleId}")
    List<Integer> rolePermissions(Integer roleId);
    @Delete("delete from t_rp where role_id = #{roleId} ")
    void deleteIdByroleId(@Param("roleId") Integer roleId);

    void insertRolePermission(@Param("rolePermissionVo") RolePermissionVo rolePermissionVo);
}
