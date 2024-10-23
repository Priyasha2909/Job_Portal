package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.Users;
import com.code.entities.UsersType;

public interface UsersTypeRespository extends JpaRepository<UsersType, Integer>{

}
