package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.account.AccountRegester;
import com.example.dto.account.LoginAccount;
import com.example.model.Account;
import com.example.service.rank1.IAccountService;

@RestController
@RequestMapping("/api")
public class AcountRestController {
	
	@Autowired
	@Qualifier("account")
	private IAccountService iAccount;
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping(value="/regester")
	public ResponseEntity<String> regester(@RequestBody AccountRegester accountRegester) {
		Account  acount	= iAccount.changetoEntity(accountRegester);
		iAccount.save(acount);
	
		return new ResponseEntity<String>("Da save", HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getAccount/{username}")
	public ResponseEntity<Account> getAccountByUsername(@PathVariable String username) {
		Account  acount	= iAccount.findByUsername(username);			
		return new ResponseEntity<Account>(acount, HttpStatus.OK);
		
	}
	
	
	@PostMapping(value="/signin")
	public ResponseEntity<String> login(@RequestBody LoginAccount aLoginAccount) {
		
	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(aLoginAccount.getUsername(), aLoginAccount.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);   
  
        return new ResponseEntity<String>("Da login", HttpStatus.OK);
	}
}
