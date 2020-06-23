package com.asiainfo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-23 22:17
 **/
@Configuration
public class ShiroConfig {

    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("DefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");

        //授权，正常情况下，没有授权跳到未授权页面
        filterMap.put("/user/add","perms[user:add]");
//        filterMap.put("/user/update","perms[user:update]");

//        filterMap.put("/user/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean ;
    }

    //DefaultWebSecurityManager
    @Bean("DefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager ;
    }



    //创建realm对象  需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
