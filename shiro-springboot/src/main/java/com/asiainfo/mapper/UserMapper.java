package com.asiainfo.mapper;


import com.asiainfo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

	public User findByName(String name);
	
	public User findById(Integer id);
}
