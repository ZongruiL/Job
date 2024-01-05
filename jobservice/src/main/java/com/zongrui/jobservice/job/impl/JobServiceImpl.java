package com.zongrui.jobservice.job.impl;


import com.zongrui.jobservice.job.Job;
import com.zongrui.jobservice.job.JobRepository;
import com.zongrui.jobservice.job.JobService;
import com.zongrui.jobservice.job.clients.CompanyClient;
import com.zongrui.jobservice.job.clients.ReviewClient;
import com.zongrui.jobservice.job.dto.JobDTO;
import com.zongrui.jobservice.job.external.Company;
import com.zongrui.jobservice.job.external.Review;
import com.zongrui.jobservice.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl  implements JobService {
    //private List<Job> jobs = new ArrayList<>();

    @Autowired
    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    int attempt = 0;

    @Override
//    @CircuitBreaker(name="companyBreaker"
//            , fallbackMethod = "companyBreakerFallback")
    @Retry(name="companyBreaker"
           , fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name="companyBreaker"
//           , fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();
        System.out.println("Attempt: "+ ++attempt);
//        for(Job job:jobs){
//
//        }

//        return jobWithCompanyDTOS;
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private JobDTO convertToDTO(Job job){

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//                });

//        List<Review> reviews = reviewResponse.getBody();
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job,company, reviews);
        return jobDTO;
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("dummy");
        return list;
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
            Job job = jobRepository.findById(id).orElse(null);
            return convertToDTO(job);
    }

    @Override
    public boolean deleteByJobId(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()){

                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setTitle(updatedJob.getTitle());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                jobRepository.save(job);
                return true;
            }
        return false;
    }


}
