package com.henry.test_springboot_demo.dao;

import com.henry.test_springboot_demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
