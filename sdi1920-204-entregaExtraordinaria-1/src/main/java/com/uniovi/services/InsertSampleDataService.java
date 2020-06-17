package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private RolesService rolesService;
	@PostConstruct
	public void init() {
		User user1 = new User("pedro33", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User userLuis = new User("luis@gmail.com", "Luis", "Fuertes");
		userLuis.setPassword("123456");
		userLuis.setRole(rolesService.getRoles()[0]);
		User user2 = new User("lucasñez", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("mariiia", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("martalmonde", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("edward", "Edward", "Núñez");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		
		usersService.addUser(user1);
		usersService.addUser(userLuis);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		
		User userp1=usersService.getUser(user1.getId());
		User userp2=usersService.getUser(user2.getId());
		User userp3=usersService.getUser(user3.getId());
		User userp4=usersService.getUser(user4.getId());
		
		friendsService.addFriend(userp1, userp2);
		friendsService.addFriend(userp1, userp3);
		friendsService.addFriendRequest(userp1, userp4);
		
	}
}