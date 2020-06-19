package com.uniovi.repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;


public interface PublicacionRepository extends CrudRepository<Publicacion, Long>
{
	//Publicacion findByUsername(String username);
	
	ArrayList<Publicacion> findAll();
	
	@Query("select r from Publicacion r where (lower(r.username) like lower(?1) )")
	ArrayList<Publicacion> searchByUsername(String text);
	
	@Query("select r from Publicacion r where (lower(r.title) like lower(?1) ) and (lower(r.text) like lower(?2) ) and (lower(r.username) like lower(?3) )")
	Publicacion searchPublicacion(String title, String text, String username);
	
	
}