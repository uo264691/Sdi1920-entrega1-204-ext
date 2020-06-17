package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendsRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class FriendsService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	@PostConstruct
	public void init() {
	}
	
	public Page<User> getFriends(Pageable pageable, String username)
	{
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		User user = usersRepository.findByUsername(username);
		if(user!=null)
			users = friendsRepository.findFriendsOf(pageable, user);
		return users;
	}
	
	public Page<User> getFriendRequests(Pageable pageable, String username)
	{
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		User user = usersRepository.findByUsername(username);
		if(user!=null)
			users = friendsRepository.findFriendRequestsOf(pageable, user);
		return users;
	}
	
	private User getUser(String username) {
		return usersRepository.findByUsername(username);
	}
	
	private User getUser(Long id) {
		Optional<User> user = usersRepository.findById(id);
		if(user.isPresent())
			return user.get();
		else return null;
	}
	
	public void addFriend(String user1username, Long user2id) {
		User user1 = getUser(user1username);
		User user2 = getUser(user2id);
		
		if(user1!=null && user2!=null) {
			addFriendRequest(user1, user2);
		}
	}

	public void acceptFriendRequest(String user1username, Long user2id) {
		User user1 = getUser(user1username);
		User user2 = getUser(user2id);
		
		Set<Friendship> fset = friendsRepository.findByUser1andUser2(user1, user2);
		
		for(Friendship f : fset)
			acceptFriendRequest(f);
	}
	
	public void deleteFriend(String user1username, Long user2id) {
		User user1 = getUser(user1username);
		User user2 = getUser(user2id);
		
		Set<Friendship> fset = friendsRepository.findByUser1andUser2(user1, user2);
		
		for(Friendship f : fset)
			deleteFriend(f);
		
	}
	
	public void addFriend(User user1, User user2) {
		friendsRepository.save(new Friendship(user1, user2, false));
		friendsRepository.save(new Friendship(user2, user1, false));
	}
	
	public void addFriendRequest(User user1, User user2) {
		if(friendsRepository.findByUser1andUser2(user2, user1).isEmpty())
			friendsRepository.save(new Friendship(user2, user1, true));
	}
	
	public void acceptFriendRequest(Friendship f) {
		if(f!=null)
			friendsRepository.acceptFriendship(f);
	}
	
	public void deleteFriend(Friendship f) {
		if(f!=null)
			friendsRepository.deleteFriendship(f);
	}
}
