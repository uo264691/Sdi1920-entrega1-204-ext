package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>
{
	User findByUsername(String username);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("select r from User r where (lower(r.name) like lower(?1) or lower(r.lastName) like lower(?1) or lower(r.username) like lower(?1))")
	Page<User> searchByNameAndSurname(Pageable pageable, String text);
	
	@Query("select r from User r where (lower(r.name) like lower(?1) or lower(r.lastName) like lower(?1) or lower(r.username) like lower(?1)) and r.role not like 'ROLE_ADMIN' and r.username not like ?2")
	Page<User> searchByNameAndSurnameNoAdminsAndUserSession(Pageable pageable, String text, String username);
}