package com.henry.springdatajpademo.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID> , // 能够使用Jpa所提供的方法
		JpaSpecificationExecutor<T>{ // 能够使用Specification
	
	Page<T> findByAuto(T example, Pageable pageable);
	

}
