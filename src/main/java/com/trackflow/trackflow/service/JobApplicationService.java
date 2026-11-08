package com.trackflow.trackflow.service;

import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.model.JobApplication;
import com.trackflow.trackflow.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    // Get applications belonging to a user
    public List<JobApplication> getAll(Long userId) {
        return repository.findAllByUser_Id(userId);
    }

    // Get application by ID and user ID
    public JobApplication getById(Long id, Long userId) {
        return repository.findByIdAndUser_Id(id, userId).orElse(null);
    }

    // Create application
    public JobApplication add(JobApplication application, AppUser user) {
        application.setId(null);
        application.setUser(user);
        return repository.save(application);
    }

    // Update application
    public JobApplication update(
            Long id, Long userId, JobApplication updatedApplication) {

        JobApplication existing =
                repository.findByIdAndUser_Id(id, userId).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setCompany(updatedApplication.getCompany());
        existing.setJobTitle(updatedApplication.getJobTitle());
        existing.setJobUrl(updatedApplication.getJobUrl());
        existing.setAppliedDate(updatedApplication.getAppliedDate());
        existing.setStatus(updatedApplication.getStatus());
        existing.setNotes(updatedApplication.getNotes());

        return repository.save(existing);
    }

    // Delete application
    public boolean deleteById(Long id, Long userId) {

        if (!repository.existsByIdAndUser_Id(id, userId)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}