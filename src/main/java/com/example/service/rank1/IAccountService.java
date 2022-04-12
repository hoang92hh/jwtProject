package com.example.service.rank1;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.dto.account.AccountRegester;
import com.example.model.Account;

public interface IAccountService extends IchangeToEntity<Account, AccountRegester>  {
		Account findByUsername(String username);
}
