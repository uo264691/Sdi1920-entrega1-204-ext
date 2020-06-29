package com.uniovi.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.LogInValidator;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private LogInValidator logInValidator;
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User activeUser = usersService.getUserByUsername(username);
		
		return "home";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("rolesList", rolesService.getRoles());
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if(result.hasErrors()) {
			return "signup";
		}
		
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	 public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated User user, BindingResult result) {
		logInValidator.validate(user, result);
		if(result.hasErrors()) {
			return "login";
		}
		securityService.autoLogin(user.getUsername(), user.getPassword());
		return "redirect:home";
	}
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable, @RequestParam(value = "", required = false) String searchText) {
		if(searchText==null)
			searchText="";
		
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		String email =auth.getName();
		//System.out.println("Email -->"+email);
		Page<User> users = usersService.searchUsersByNameAndSurnameNoAdminsAndUserSession(pageable, searchText, email);
		//System.out.println(usersService.getUserByUsername(email).getRole());
		if(usersService.getUserByUsername(email).getRole().equals("ROLE_ADMIN")) {
			//System.out.println(usersService.getUserByUsername(email).getRole());
			users = usersService.getUsers(pageable);
		}
			
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}
	@RequestMapping("/comunidades/list")
	public String getListadoComunidades(Model model) {
		//System.out.println("Email -->"+email);
		List<User> users = usersService.getUsers();
		//System.out.println(usersService.getUserByUsername(email).getRole());
			
		model.addAttribute("userList", users);

		return "comunidades/list";
	}
	
	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "user/list :: tableUsers";
	}

	@RequestMapping(value = "/user/add")
	public String getUser(Model model) {
		model.addAttribute("rolesList", rolesService.getRoles());
		model.addAttribute("usersList", usersService.getUsers());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		user.setPassword("");
		user.setPasswordConfirm("");
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		User original = usersService.getUser(id);
		// modificar solo score y description
		original.setUsername(user.getUsername());
		original.setName(user.getName());
		original.setLastName(user.getLastName());
		usersService.addUser(original);
		return "redirect:/user/details/" + id;
	}
}