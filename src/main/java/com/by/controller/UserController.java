package com.by.controller;

import com.by.model.User;
import com.by.service.UserService;
import com.by.vo.User1Vo;
import com.by.vo.UserRoleVo;
import com.by.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @GetMapping("list")
    public Map<String, Object> list(Integer page,Integer limit){
        Map<String,Object> mappage = new HashMap <>();
        //PageHelper.startPage(page,limit);
        mappage.put("start",(page-1)*limit);
        mappage.put("limit",limit);
        List<User1Vo> list = userService.findAll(mappage);
        //PageInfo<User1Vo> info = new PageInfo <>(list);
        Map<String,Object> map = new HashMap <>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }
    @RequiresPermissions("/user/add")
    @ResponseBody
    @PostMapping("add")
    public String add(User user){
        try {
            userService.add(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequiresPermissions("/user/delete")
    @ResponseBody
    @DeleteMapping("delete/{userId}")
    public String delete(@PathVariable("userId") Integer userId){
        try {
            userService.delete(userId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequiresPermissions({"/user/update"})
    @ResponseBody
    @RequestMapping("selectById/{id}")
    public User selectById(@PathVariable("id") Integer userId){
        User user = userService.selectById(userId);
        return user;
    }
    @ResponseBody
    @RequestMapping("update")
    public String update(User user){
        try {
            userService.update(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @ResponseBody
    @GetMapping("userRoles")
    public Map<String,Object> userRoles(Integer userId){
        Map<String,Object> map = userService.userRoles(userId);
        return map;
    }
    @ResponseBody
    @PostMapping("userRole")
    public Map<String,Object> userRole(UserRoleVo userRoleVo){
       Map<String,Object> map = new HashMap <>();
        try {
            userService.userRole(userRoleVo);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
       return map;

    }
    @RequestMapping("login")
    public String login(@RequestParam("userName") String userName, @RequestParam("userPswd") String userPswd){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(userName,userPswd);
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                System.out.println("登陆失败：户名不存在");
            } catch (IncorrectCredentialsException e){
                System.out.println("密码错误");
            } catch (AuthenticationException e){
                System.out.println("登陆失败");
            }
        }
        return "redirect:/test.jsp";
    }
   @RequestMapping("register")
   public String register(User user, ModelMap map) {
       User user1 = userService.selectByName(user);
       if(user1 == null){
           ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
           String simpleHash = String.valueOf(new SimpleHash("Md5",user.getUserPswd(),credentialsSalt,3));
           user.setUserPswd(simpleHash);
           userService.addUser(user);
           map.put("success","注册成功");
           return "redirect:/test.jsp";
       }
       map.put("error","注册失败");
      return "/register.jsp";
   }
}
