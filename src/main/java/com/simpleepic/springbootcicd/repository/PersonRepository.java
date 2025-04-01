// PersonRepository.java
package com.simpleepic.springbootcicd.repository;

import com.simpleepic.springbootcicd.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}