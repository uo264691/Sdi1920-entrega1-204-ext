package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.FriendsService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class FriendsController {
	@Autowired
	private FriendsService friendsService;
	
	@RequestMapping(value = "/friend/list")
	public String getFriendList(Model model, Principal principal, Pageable pageable) {
		Page<User> users = friendsService.getFriends(pageable, principal.getName());
		
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "friend/list";
	}
	
	@RequestMapping(value = "/friend/add/{id}")
	public String getAdd(Model model, Principal principal, @PathVariable Long id) {
		if(!friendsService.requestPossible(principal.getName(), id)) {
			return "redirect:/user/list?x=1";
		}
		friendsService.addFriend(principal.getName(), id);
		return "redirect:/request/list";
	}
	
	@RequestMapping(value = "/request/accept/{id}")
	public String getAccept(Model model, Principal principal, @PathVariable Long id) {
		friendsService.acceptFriendRequest(principal.getName(), id);
		return "redirect:/friend/list";
	}

	@RequestMapping(value = "/request/delete/{id}")
	public String getRequestDelete(Model model, Principal principal, @PathVariable Long id) {
		friendsService.deleteFriend(principal.getName(), id);
		return "redirect:/request/list";
	}

	@RequestMapping(value = "/friend/delete/{id}")
	public String getFriendDelete(Model model, Principal principal, @PathVariable Long id) {
		friendsService.deleteFriend(principal.getName(), id);
		return "redirect:/friend/list";
	}
	
	@RequestMapping("/request/list")
	public String getRequestList(Model model, Principal principal, Pageable pageable) {
		Page<User> users = friendsService.getFriendRequests(pageable, principal.getName());
		
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "request/list";
	}
}
