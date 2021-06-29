package com.henry.service_demo_person.dao;

import com.henry.service_demo_person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
