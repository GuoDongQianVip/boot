package com.by.service.Impl;

import com.by.mapper.PermissionMapper;
import com.by.model.Permission;
import com.by.model.Role;
import com.by.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List <Permission> findAll() {
        return permissionMapper.findAll();
    }
    @Transactional
    @Override
    public void add(Permission permission) {
        permissionMapper.add(permission);
    }
    @Transactional
    @Override
    public Permission selectById(Integer permissionId) {
        Permission permission =permissionMapper.selectById(permissionId);
        return permission;
    }
    @Transactional
    @Override
    public void update(Permission permission) {
        permissionMapper.update(permission);

    }

    @Override
    public void deleteById(Integer permissionId) {
        permissionMapper.deleteById(permissionId);
    }
}
