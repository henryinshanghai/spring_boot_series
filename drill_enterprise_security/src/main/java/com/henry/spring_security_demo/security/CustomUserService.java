package com.henry.spring_security_demo.security;

import com.henry.spring_security_demo.dao.SysUserRepository;
import com.henry.spring_security_demo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService { //1 自定义的service需要实现 UserDetailService接口
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) { //2 重写 loadUserByUsername()方法 来 获得用户。
		
		SysUser user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		
		return user; //3 由于自定义的用户类型 实现了 UserDetails接口。所以此处可以直接返回给Spring Security使用
	}

}
