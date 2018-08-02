package cn.jxnu.mapper;

import cn.jxnu.domain.User;

public interface UserMapper {
	
	User findByUsername(String username);
	
	User fingById(Integer id);
}
