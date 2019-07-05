package com.by.controller;

import com.by.model.Role;
import com.by.model.User;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @ResponseBody
    @RequestMapping("list")
    public List<Role> list(){
        List <Role> list =  roleService.list();
        return list;
    }
    @ResponseBody
    @PostMapping("add")
    public String add(Role role){
        try {
            roleService.add(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
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
}
