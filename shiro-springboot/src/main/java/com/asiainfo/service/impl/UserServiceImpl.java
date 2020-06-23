package com.asiainfo.service.impl;

import com.asiainfo.domain.User;
import com.asiainfo.mapper.UserMapper;
import com.asiainfo.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	//注入Mapper接口
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

}
