package com.code.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.code.entities.RecruiterProfile;
import com.code.entities.Users;
import com.code.repositories.UsersRepository;
import com.code.services.RecruiterProfileService;
import com.code.util.FileUploadUtil;

import ch.qos.logback.core.util.StringUtil;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {
	
	private final UsersRepository usersRepository;
	private final RecruiterProfileService recruiterProfileService;
	
 
	public RecruiterProfileController(UsersRepository usersRepository,RecruiterProfileService recruiterProfileService) {
		
		this.usersRepository = usersRepository;
		this.recruiterProfileService = recruiterProfileService;
	}


	@GetMapping("/")
	public String recruiterProfile(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users = usersRepository.findByEmail(currentUsername).orElseThrow(()->new UsernameNotFoundException("Could not found the user"));
			Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());
		
		    if(!recruiterProfile.isEmpty()) {
		    	model.addAttribute("profile", recruiterProfile.get());
		    }
		    
		    
		}
		return "recruiter_profile";
	}
	
	
	//creating new recruiter profile in memory based on form data
	@PostMapping("/addNew")
	public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users = usersRepository.findByEmail(currentUsername).orElseThrow(()->new UsernameNotFoundException("Could not found the user"));
			
		//associate recruiter profile with existing user account	
		  recruiterProfile.setUserId(users);
		  recruiterProfile.setUserAccountId(users.getUserId());
		  
			
		}
		model.addAttribute("profile",recruiterProfile);
		
		String fileName="";
		
		if(!multipartFile.getOriginalFilename().equals("")) {
			fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			recruiterProfile.setProfilePhoto(fileName);
		}
		
		//save profile to db
		RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);
		
		String uploadDir = "photos/recruiter/" + savedUser.getUserAccountId();
		
		try {
			
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/dashboard/";
		
	}
}
