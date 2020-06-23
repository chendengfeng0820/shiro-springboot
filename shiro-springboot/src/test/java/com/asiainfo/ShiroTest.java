package com.asiainfo;

import com.asiainfo.service.UserService;
import com.asiainfo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName ShiroTest
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-24 01:00
 **/
@SpringBootTest
@MapperScan("com.asiainfo.mapper")
public class ShiroTest {


    @Autowired
    UserService userService;

    @Test
    public void test1(){
        System.out.println(userService.findByName("cdf"));
    }
}
