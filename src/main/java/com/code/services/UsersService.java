package com.code.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code.entities.JobSeekerProfile;
import com.code.entities.RecruiterProfile;
import com.code.entities.Users;
import com.code.repositories.JobSeekerRepository;
import com.code.repositories.RecruiterProfileRepository;
import com.code.repositories.UsersRepository;



@Service
public class UsersService {
  
     private final UsersRepository usersRepository;
     private final RecruiterProfileRepository recruiterProfileRepository;
     private final JobSeekerRepository jobSeekerRepository;
     
     private final PasswordEncoder passwordEncoder;
     



	public UsersService(UsersRepository usersRepository,RecruiterProfileRepository recruiterProfileRepository, JobSeekerRepository jobSeekerRepository,PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.recruiterProfileRepository = recruiterProfileRepository;
		this.jobSeekerRepository = jobSeekerRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Users addNew(Users users) {
        users.setIsActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        
        //to encrypt password
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        
        Users savedUser = usersRepository.save(users);
        int userId = users.getUserTypeId().getUserTypeId();
        
        if(userId==1) {
        	recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else {
        	jobSeekerRepository.save(new JobSeekerProfile(savedUser));
        }
        
        return savedUser;
    }

     public Optional<Users> getUserByEmail(String email){
    	 return usersRepository.findByEmail(email);
     }

	public Object getCurrentUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//it should not be anonymous
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String name = authentication.getName();
			Users users = usersRepository.findByEmail(name).orElseThrow(()->new UsernameNotFoundException("Could not found the user"));
			int userId = users.getUserId();
			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
                return recruiterProfile;
            } else {
                JobSeekerProfile jobSeekerProfile = jobSeekerRepository.findById(userId).orElse(new JobSeekerProfile());
                return jobSeekerProfile;
            }
        }

        return null;
			
		
		}

	public Users getCurrentUser() {
       
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//it should not be anonymous
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String name = authentication.getName();
			Users user = usersRepository.findByEmail(name).orElseThrow(()->new UsernameNotFoundException("Could not found the user"));
		return user;
	}
		return null;
	}

	public Users findByEmail(String currentUsername) {
		return usersRepository.findByEmail(currentUsername).orElseThrow(()->new UsernameNotFoundException("USer could not be found"));
	}
}
    


