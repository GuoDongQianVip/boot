package com.by.service.Impl;

import com.by.mapper.RoleMapper;
import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Transactional
    @Override
    public List <Role> list() {
        return roleMapper.list();
    }
    @Transactional
    @Override
    public void add(Role role) {
        roleMapper.add(role);
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
}
