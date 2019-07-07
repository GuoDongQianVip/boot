package com.by.controller;

import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import com.by.vo.RolePermissionVo;
import com.by.vo.RoleVo;
import com.by.vo.User1Vo;
import com.by.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @ResponseBody
    @GetMapping("list")
    public Map <String, Object> list(Integer page, Integer limit){
        Map<String,Object> mappage = new HashMap <>();
        //PageHelper.startPage(page,limit);
        mappage.put("start",(page-1)*limit);
        mappage.put("limit",limit);
        List<RoleVo> list = roleService.findAll(mappage);
        //PageInfo<User1Vo> info = new PageInfo <>(list);
        Map<String,Object> map = new HashMap <>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }
    @ResponseBody
    @PostMapping("add")
    public Map<String,Object> add(String roleName){
        Map<String,Object> map = new HashMap <>();
        try {
            roleService.add(roleName);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return map;

    }
    @ResponseBody
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer roleId){
        try {
            roleService.deleteById(roleId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @ResponseBody
    @GetMapping("selectById/{roleId}")
    public Role selectById(@PathVariable("roleId") Integer roleId){
        Role role = roleService.selectById(roleId);
        return role;
    }
    @ResponseBody
    @PutMapping("update")
    public String update(Role role){
        try {
            roleService.update(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @ResponseBody
    @GetMapping("rolePermissions")
    public Map<String,Object> rolePermissions(Integer roleId){
        Map<String,Object> map = roleService.rolePermissions(roleId);
        return map;
    }
    @ResponseBody
    @PostMapping("rolePermission")
    public Map<String,Object> rolePermission(RolePermissionVo rolePermissionVo){
        Map<String,Object> map = new HashMap <>();
        try {
            roleService.rolePermission(rolePermissionVo);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return map;

    }
}
