package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Person;

public interface PersonRepository extends JpaRepository<Person,Integer>
{
	@Query("select case when count(s) >0 then true else false end from Person s where s.id=?1")
	boolean isPersonExistsById(Integer id);
}
