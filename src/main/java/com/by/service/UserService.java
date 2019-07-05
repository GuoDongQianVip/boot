package com.by.service;

import com.by.model.User;
import com.by.vo.User1Vo;
import com.by.vo.UserRoleVo;
import com.by.vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    List<User1Vo> findAll(Map<String,Object> mappage);

    void add(User user);

    void delete(Integer userId);

    User selectById(Integer userId);

    void update(User user);

    Map <String, Object> userRoles(Integer userId);

    void userRole(UserRoleVo userRoleVo);

    User findByName(String username);

    Set<String> findRoleByUserName(String userName);

    Set<String> findPermissionByUserName(String userName);

    void addUser(User user);

    User selectByName(User user);
}
