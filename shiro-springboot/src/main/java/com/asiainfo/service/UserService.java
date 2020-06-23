package com.asiainfo.service;


import com.asiainfo.domain.User;

public interface UserService {

	public User findByName(String name);
	
	public User findById(Integer id);
}
