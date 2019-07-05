package com.by.service.Impl;

import com.by.mapper.RoleMapper;
import com.by.mapper.UuserMapper;
import com.by.model.Role;
import com.by.model.User;
import com.by.model.UserExample;
import com.by.model.UserRole;
import com.by.service.UserService;
import com.by.vo.User1Vo;
import com.by.vo.UserRoleVo;
import com.by.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UuserMapper uuserMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List <User1Vo> findAll(Map<String,Object> mappage) {
        List<User1Vo> list=uuserMapper.findAll(mappage);
        return list;
    }

    @Override
    public void add(User user) {
        uuserMapper.add(user);
    }

    @Override
    public void delete(Integer userId) {
        uuserMapper.deleteById(userId);
    }

    @Override
    public User selectById(Integer userId) {
        User user = uuserMapper.selectById(userId);
        return user;
    }

    @Override
    public void update(User user) {
        uuserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Map <String, Object> userRoles(Integer userId) {
        Map <String, Object> map = new HashMap <>();
        //1.查找到所有的roles
        List<Role> roles = roleMapper.findAll();
        //2.查找到所有的roleid
        List<Integer> roleIds = uuserMapper.selectIdsByUserId(userId);
        map.put("roles",roles);
        map.put("roleIds",roleIds);
        return map;
    }
    @Transactional
    @Override
    public void userRole(UserRoleVo userRoleVo) {
        //1.删除掉所有和userId有关的数据
        uuserMapper.deleteIdByUserId(userRoleVo.getUserId());

        //2.添加新的数据
        uuserMapper.insertUserRole(userRoleVo);
    }

    @Override
    public User findByName(String username) {
        User user = uuserMapper.findByName(username);
        return user;
    }

    @Override
    public Set <String> findRoleByUserName(String userName) {
        return uuserMapper.findRoleByUserName(userName);
    }

    @Override
    public Set <String> findPermissionByUserName(String userName) {
        return uuserMapper.findPermissionByUserName(userName);
    }

    @Override
    public void addUser(User user) {
        uuserMapper.addUser(user);
    }

    @Override
    public User selectByName(User user) {
        User user1 = uuserMapper.selectByName(user);
        return null;
    }
}
