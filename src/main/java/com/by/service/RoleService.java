package com.by.service;

import com.by.model.Role;
import com.by.model.User;

import java.util.List;

public interface RoleService {
    List<Role> list();

    void add(Role role);

    Role selectById(Integer roleId);

    void update(Role role);

    void deleteById(Integer roleId);
}
