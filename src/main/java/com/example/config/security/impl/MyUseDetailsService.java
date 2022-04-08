package com.example.config.security.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.dao.repository.AccountRepository;
import com.example.model.Account;
import com.example.model.Role;




@Component
public class MyUseDetailsService {
	
//	@Autowired
//	private AccountRepository accountRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Account account = accountRepository.findByUsername(username);
//		if(account == null) {
//			return null;
//		}
//		
////		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
////		userDetailsImpl.setUsername(account.getUsername());
////		userDetailsImpl.setPassword(account.getPassword());
////		userDetailsImpl.setEmail(account.getEmail());
////		userDetailsImpl.setPhone(account.getPhone());	
////		userDetailsImpl.setRoles(account.getRoles());////		
////		return userDetailsImpl;
////		
//		Set<Role> roleSet =  account.getRoles();
//		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//		for (Role role : roleSet) {
//			authList.add(new SimpleGrantedAuthority(role.getAuthority()));
//		}
//		
//		UserDetails uDetails = new org.springframework.security.core.userdetails.User(username, account.getPassword(), authList);
//		
//		return uDetails ;
//	}
}
