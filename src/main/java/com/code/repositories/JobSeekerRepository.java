package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.JobSeekerProfile;

public interface JobSeekerRepository extends JpaRepository<JobSeekerProfile, Integer>{

}
