package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.RecruiterProfile;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Integer> {
   
}
