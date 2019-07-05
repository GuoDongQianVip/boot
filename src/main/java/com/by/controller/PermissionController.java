package com.by.controller;

import com.by.model.Permission;
import com.by.model.User;
import com.by.service.PermissionService;
import com.by.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @PostMapping("list")
    public List <Permission> list(){
        List<Permission> list = permissionService.findAll();
        return list;
    }
    @ResponseBody
    @PostMapping("add")
    public String add(Permission permission){
        try {
            permissionService.add(permission);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @ResponseBody
    @DeleteMapping("delete/{permissionId}")
    public String delete(@PathVariable("permissionId") Integer permissionId){
        try {
            permissionService.deleteById(permissionId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @ResponseBody
    @RequestMapping("selectById/{id}")
    public Permission selectById(@PathVariable("id") Integer permissionId){
        Permission permission = permissionService.selectById(permissionId);
        return permission;
    }
    @ResponseBody
    @PutMapping("update")
    public String update(Permission permission){
        try {
            permissionService.update(permission);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
