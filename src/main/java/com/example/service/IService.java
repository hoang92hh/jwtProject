package com.example.service;

import com.example.controller.AcountController;
import com.example.model.Account;

public interface IService<E> {
	void  save(E element);
	E findById(Long id);
	
	
}
