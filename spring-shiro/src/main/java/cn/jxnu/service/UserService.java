package cn.jxnu.service;

import cn.jxnu.domain.User;

public interface UserService {
	User findByUsername(String username);
	
	User fingById(Integer id);
}
