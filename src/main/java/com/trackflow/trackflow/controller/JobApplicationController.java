package com.trackflow.trackflow.controller;

import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.model.JobApplication;
import com.trackflow.trackflow.repository.AppUserRepository;
import com.trackflow.trackflow.service.JobApplicationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;
    private final AppUserRepository userRepository;

    public JobApplicationController(
            JobApplicationService service,
            AppUserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    // Get logged-in user
    private AppUser getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "User not found"));
    }

    // GET all applications
    @GetMapping
    public List<JobApplication> getAllApplications(
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);
        return service.getAll(user.getId());
    }

    // GET application by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(
            @PathVariable Long id,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        JobApplication application = service.getById(id, user.getId());

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application);
    }

    // POST create application
    @PostMapping
    public JobApplication createApplication(
            @RequestBody JobApplication application,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);
        return service.add(application, user);
    }

    // PUT update application
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        JobApplication updated =
                service.update(id, user.getId(), application);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE application
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long id,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        boolean deleted = service.deleteById(id, user.getId());

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}