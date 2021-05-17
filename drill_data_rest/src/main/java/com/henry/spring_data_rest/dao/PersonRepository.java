package com.henry.spring_data_rest.dao;

import com.henry.spring_data_rest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

// 定制节点的路径
@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person, Long> {

    // 把 repository中的DAO操作 暴露成为 REST资源
    @RestResource(path = "nameStartsWith", rel="nameStartsWith")
    Person findByNameStartsWith(String name);
}
