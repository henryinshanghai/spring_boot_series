package com.henry.dirll_data_transaction.dao;

import com.henry.dirll_data_transaction.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
