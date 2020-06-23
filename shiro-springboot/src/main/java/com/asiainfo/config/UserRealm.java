package com.asiainfo.config;

import com.asiainfo.domain.User;
import com.asiainfo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.AuthorizationDataEntry;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-23 22:17
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了==>授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
//        info.addStringPermission("user:update");
        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了==>认证");



        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //连接数据库
        User user = userService.findByName(userToken.getUsername());

        if(user==null){
            return null;
        }

        //密码认证 shiro做
        return  new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
