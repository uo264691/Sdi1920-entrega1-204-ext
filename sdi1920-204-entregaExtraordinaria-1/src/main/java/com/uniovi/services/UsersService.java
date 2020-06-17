package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.FriendsRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public User addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return usersRepository.save(user);
	}
	
	public User getUserByUsername(String dni) {
		return usersRepository.findByUsername(dni);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public Page<User> searchUsersByNameAndSurname(Pageable pageable, String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText= "%"+searchText+"%";
		
		users = usersRepository.searchByNameAndSurname(pageable, searchText);
		return users;
	}
	
	public Page<User> searchUsersByNameAndSurnameNoAdminsAndUserSession(Pageable pageable, String searchText, String username) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText= "%"+searchText+"%";
		
		users = usersRepository.searchByNameAndSurnameNoAdminsAndUserSession(pageable, searchText, username);
		return users;
	}
	
}