package com.henry.spring_security_demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class SysUser implements UserDetails{ //1 把自定义的User实现 UserDetails - 这样自定义的类型就会成为 Spring Security中的用户
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	// 2 声明 user -> role的关系是：多对多
	@ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
	private List<SysRole> roles;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 3 重写getAuthorities()方法 - 把用户的角色作为权限的标识
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<SysRole> roles=this.getRoles();
		for(SysRole role:roles){
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	
	
	
	
}
