package com.zongrui.jobservice.job.mapper;

import com.zongrui.jobservice.job.Job;
import com.zongrui.jobservice.job.dto.JobDTO;
import com.zongrui.jobservice.job.external.Company;
import com.zongrui.jobservice.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompany(company);
        jobDTO.setId(job.getId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
