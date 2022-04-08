package com.example.service.rank1.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.repository.AccountRepository;
import com.example.dto.account.AccountRegester;
import com.example.model.Account;
import com.example.model.Role;
import com.example.service.rank1.IAccountService;


@Service()
@Qualifier("account")
public class AccountService implements IAccountService,UserDetailsService{
	
	
	@Autowired	
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Account findById(Long id) {
		return accountRepository.getById(id);
		
	}


	@Override
	public void save(Account element) {	
		if(accountRepository.findByUsername(element.getUsername())==null
			&& accountRepository.findByEmail(element.getEmail())==null	
			&& accountRepository.findByPhone(element.getPhone())==null		
		) {
			accountRepository.save(element);
		}
		
	}
	
	
	


	@Override
	public Account changetoEntity(AccountRegester accountRegester) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.setUsername(accountRegester.getUsername());
		account.setPassword(passwordEncoder.encode(accountRegester.getPassword()));
		account.setEmail(accountRegester.getEmail());
		account.setPhone(accountRegester.getPhone());
		Timestamp createdTime = new Timestamp(System.currentTimeMillis());
		account.setReg_date(createdTime);
		
		Set<Role> roles = new HashSet<>();
		for (int i = 0; i<accountRegester.getRoleList().length; i++ ) {
			
			String id = accountRegester.getRoleList()[i];
			
			roles.add(new com.example.model.Role(Long.parseLong(id)) );
		}
		account.setRoles(roles);
		
		return account;	}


	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new RuntimeException("Canh chim hoang yen gay~");
		}
	
		Set<Role> roleSet =  account.getRoles();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (Role role : roleSet) {
			authList.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		UserDetails uDetails = new org.springframework.security.core.userdetails.User(username, account.getPassword(), authList);
		
		return uDetails ;
	}
	
	
}
