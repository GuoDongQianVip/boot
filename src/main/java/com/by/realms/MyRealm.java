package com.by.realms;

import com.by.model.User;
import com.by.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = null;
        if(!StringUtils.isEmpty(username)){
            user = userService.findByName(username);
        }

        //Object principal, Object credentials, String realmName
        //用户名，密码，当前realm 名称
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if(user != null){
            String principal = user.getUserName();
            String credentials = user.getUserPswd();
            String realmName = getName();
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        }

        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles =userService.findRoleByUserName(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        Set<String> permissions =userService.findPermissionByUserName(userName);
        info.addStringPermissions(permissions);

        return info;
    }
}
