package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendsRepository;
import com.uniovi.repositories.PublicacionRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class PublicacionService {
	@Autowired
	private PublicacionRepository publicacionRepository;
	
	
	
	@PostConstruct
	public void init() {
	}

	public ArrayList<Publicacion> getPublicacionesByUsername(String username) {
		ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();
		publicaciones = publicacionRepository.searchByUsername(username);
		//publicaciones = publicacionRepository.findByUsername(username);
		return publicaciones;
	}
	
	public Publicacion getPublicacion(Long id) {
		return publicacionRepository.findById(id).get();
	}
	
	public Publicacion addPublicacion(Publicacion publicacion) {
		return publicacionRepository.save(publicacion);
	}
	
	public void deletePublicacion(String title, String text, String username) {
		Publicacion p = publicacionRepository.searchPublicacion(title, text, username);
		publicacionRepository.deleteById(p.getId());
	}
	
}