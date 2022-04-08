package com.example.service.rank1;

import com.example.service.IService;

public interface IchangeToEntity<E,G> extends IService<E> {
	E changetoEntity(G inputElement);
}
