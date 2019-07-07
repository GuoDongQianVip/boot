package com.by.service;

import com.by.model.Role;
import com.by.model.User;
import com.by.vo.RolePermissionVo;
import com.by.vo.RoleVo;
import com.by.vo.User1Vo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    void add(String roleName);

    Role selectById(Integer roleId);

    void update(Role role);

    void deleteById(Integer roleId);

    List<RoleVo> findAll(Map<String, Object> mappage);

    Map<String, Object> rolePermissions(Integer roleId);

    void rolePermission(RolePermissionVo rolePermissionVo);
}
