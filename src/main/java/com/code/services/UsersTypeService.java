package com.code.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entities.UsersType;
import com.code.repositories.UsersTypeRespository;

@Service
public class UsersTypeService {
  
	private final UsersTypeRespository usersTypeRespository;
	
	public UsersTypeService(UsersTypeRespository usersTypeRespository) {
		
		this.usersTypeRespository = usersTypeRespository;
	}
	
	
	public List<UsersType> getAll(){
		
		return usersTypeRespository.findAll();
	}
	
}
