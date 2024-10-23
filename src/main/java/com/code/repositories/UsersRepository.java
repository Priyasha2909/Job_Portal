package com.code.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    
	Optional<Users> findByEmail(String email);
}
