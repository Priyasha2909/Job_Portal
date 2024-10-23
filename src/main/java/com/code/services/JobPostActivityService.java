package com.code.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.code.entities.RecruiterJobsDto;
import com.code.entities.IRecruiterJobs;
import com.code.entities.JobCompany;
import com.code.entities.JobLocation;
import com.code.entities.JobPostActivity;
import com.code.repositories.JobPostActivityRepository;

@Service
public class JobPostActivityService {
   
	private final JobPostActivityRepository jobPostActivityRepository;

	public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
		
		this.jobPostActivityRepository = jobPostActivityRepository;
	}
	
	public JobPostActivity addNew(JobPostActivity jobPostActivity) {
		
		return jobPostActivityRepository.save(jobPostActivity);
	}

	public List<com.code.entities.RecruiterJobsDto> getRecruiterJobs(int recruiter) {
		List<IRecruiterJobs> recruiterJobsDto = jobPostActivityRepository.getRecruiterJobs(recruiter);
		
		List<com.code.entities.RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
		
		//convert info from DB to DTOs
		  for (IRecruiterJobs rec : recruiterJobsDto) {
	            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
	            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
	            recruiterJobsDtoList.add(new com.code.entities.RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(),
	                    rec.getJob_title(), loc, comp));
	        }
	        return recruiterJobsDtoList;
	}

	public JobPostActivity getOne(int id) {
		
		return jobPostActivityRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found"));
		
	}
	
	 public List<JobPostActivity> getAll() {
	        return jobPostActivityRepository.findAll();
	    }

	    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
	        return Objects.isNull(searchDate) ? jobPostActivityRepository.searchWithoutDate(job, location, remote,type) :
	                jobPostActivityRepository.search(job, location, remote, type, searchDate);
	    }
	
}
