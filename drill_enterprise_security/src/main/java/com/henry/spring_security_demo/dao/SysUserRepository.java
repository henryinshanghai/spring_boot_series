package com.henry.spring_security_demo.dao;

import com.henry.spring_security_demo.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long>{
	
	SysUser findByUsername(String username); // 根据用户名查询出用户

}
