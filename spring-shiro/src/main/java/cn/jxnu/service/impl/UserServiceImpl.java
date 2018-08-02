package cn.jxnu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxnu.domain.User;
import cn.jxnu.mapper.UserMapper;
import cn.jxnu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByUsername(String username) {
		User user = userMapper.findByUsername(username);
		return user;
	}

	@Override
	public User fingById(Integer id) {
		User user = userMapper.fingById(id);
		return user;
	}

}
