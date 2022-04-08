package com.example.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.account.AccountRegester;
import com.example.dto.account.LoginAccount;
import com.example.service.rank1.IAccountService;

@Controller
public class AcountController {
	
	@Autowired
	@Qualifier("account")
	private IAccountService account;
	
	

	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
 
		String formattedDate = dateFormat.format(date);
 
		model.addAttribute("serverTime", formattedDate );
 
		return "index";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getAccount(Model model) {
		System.out.println("ok");
//		model.addAttribute("username", account.findById(id).getUsername());
		return "home";
	}
	
	
//	@GetMapping(value="/regester")
//	public String regester() {
//		
//		return "regester";
//	}
//	@GetMapping(value="/form/signin")
//	public String siginform() {
//		
//		return "signin";
//	}
	
}
