package com.zongrui.jobservice.job;

import com.zongrui.jobservice.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(Long id);

    boolean deleteByJobId(Long id);

    boolean updateJob(Long id, Job job);
}
