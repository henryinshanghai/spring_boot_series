package com.henry.chapter08_0503.dao;

import com.henry.chapter08_0503.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
