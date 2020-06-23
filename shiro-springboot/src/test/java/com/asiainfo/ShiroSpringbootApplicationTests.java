package com.asiainfo;

import com.asiainfo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.asiainfo.mapper")
class ShiroSpringbootApplicationTests {


	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		System.out.println(userService.findByName("cdf"));

	}

}
