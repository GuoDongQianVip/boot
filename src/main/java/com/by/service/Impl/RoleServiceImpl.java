package com.by.service.Impl;

import com.by.mapper.PermissionMapper;
import com.by.mapper.RoleMapper;
import com.by.model.Permission;
import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import com.by.vo.RolePermissionVo;
import com.by.vo.RoleVo;
import com.by.vo.User1Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    /*@Transactional
    @Override
    public Map<String,Object> add(String roleName) {
        Map map =new HashMap();
        roleMapper.add(roleName);
        return Map;
    }*/

    @Override
    public void add(String roleName) {
        roleMapper.add(roleName);
    }

    @Transactional
    @Override
    public Role selectById(Integer roleId) {
        Role role =roleMapper.selectById(roleId);
        return role;
    }
    @Transactional
    @Override
    public void update(Role role) {
        roleMapper.update(role);

    }

    @Override
    public void deleteById(Integer roleId) {
        roleMapper.deleteById(roleId);
    }

    @Override
    public List <RoleVo> findAll(Map <String, Object> mappage) {
        return roleMapper.select(mappage);
    }

    @Override
    public Map <String, Object> rolePermissions(Integer roleId) {
        Map<String,Object> map = new HashMap <>();
        //1.查找到所有的permissions
        List<Permission> permissions = permissionMapper.select();
        //2.查找到所有的permissionId
        List<Integer> permissionIds = roleMapper.rolePermissions(roleId);
        map.put("permissions",permissions);
        map.put("permissionIds",permissionIds);
        return map;
    }

    @Override
    public void rolePermission(RolePermissionVo rolePermissionVo) {
        roleMapper.deleteIdByroleId(rolePermissionVo.getRoleId());

        //2.添加新的数据
        roleMapper.insertRolePermission(rolePermissionVo);
    }

}
