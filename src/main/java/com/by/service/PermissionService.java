package com.by.service;

import com.by.model.Permission;
import com.by.model.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void add(Permission permission);

    Permission selectById(Integer permissionId);

    void update(Permission permission);

    void deleteById(Integer permissionId);
}
