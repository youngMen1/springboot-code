package com.seal.security.service.impl;


import com.seal.security.model.User;
import com.seal.security.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * 用户管理
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:32
 **/
@Service
public class SysUserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username) {
		User user = new User();
		user.setId(1L);
		user.setUsername(username);
		String password = new BCryptPasswordEncoder().encode("123");
		user.setPassword(password);
		return user;
	}

	@Override
	public Set<String> findPermissions(String username) {
		Set<String> permissions = new HashSet<>();
		permissions.add("sys:user:view");
		permissions.add("sys:user:add");
		permissions.add("sys:user:edit");
		permissions.add("sys:user:delete");
		return permissions;
	}

}
