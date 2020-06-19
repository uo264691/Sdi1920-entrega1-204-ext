package com.uniovi.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.uniovi.services.PublicacionService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.LogInValidator;
import com.uniovi.validators.PublicacionValidator;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class PublicacionController {
	@Autowired
	private PublicacionService publicacionService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FriendsService friendsService;
	
	@Autowired
	private PublicacionValidator publicacionValidator;
	
	
	@RequestMapping(value = { "/publicacion/add" }, method = RequestMethod.GET)
	public String addPublicacion(Model model) {
		
		model.addAttribute("publicacion", new Publicacion());
		
		return "publicacion/add";
	}

	@RequestMapping(value = "/publicacion/add", method = RequestMethod.POST)
	public String signup(@ModelAttribute Publicacion publicacion, BindingResult result) {
		publicacionValidator.validate(publicacion, result);
		if(result.hasErrors()) {
			return "publicacion/add";
		}
		//System.out.println(publicacion.getTitle() +" "+ publicacion.getText());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		publicacion.setUsername(username);
		publicacion.setDate(new Date());
		publicacionService.addPublicacion(publicacion);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/publicacion/list")
	public String getFriendList(Model model, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		ArrayList<Publicacion> publicaciones = publicacionService.getPublicacionesByUsername(username);
		model.addAttribute("publicacionList", publicaciones);
		return "publicacion/list";
	}
	
	@RequestMapping(value = { "/test/reiniciar" }, method = RequestMethod.GET)
	public String reiniciarTest(Model model) {
		
		//borro publicacion
		publicacionService.deletePublicacion("Titulazo", "Textazo", "pedro33");
		
		//Borro amistad (en ambos sentido por si acaso)
		friendsService.deleteFriend("luis@gmail.com",usersService.getUserByUsername( "pedro33").getId());
		friendsService.deleteFriend( "pedro33",usersService.getUserByUsername("luis@gmail.com").getId());
		
		//borro a jony
		usersService.deleteUser(usersService.getUserByUsername("jony@gmail.com").getId());
		
		return "redirect:home";
	}

}