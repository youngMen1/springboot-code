package com.seal.security.service;

import com.seal.security.model.User;

import java.util.Set;


/**
 * 用户管理
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:32
 **/
public interface UserService {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

}
