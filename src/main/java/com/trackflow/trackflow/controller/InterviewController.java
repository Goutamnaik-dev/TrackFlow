package com.trackflow.trackflow.controller;

import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.model.Interview;
import com.trackflow.trackflow.repository.AppUserRepository;
import com.trackflow.trackflow.service.InterviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;
    private final AppUserRepository userRepository;

    public InterviewController(
            InterviewService interviewService,
            AppUserRepository userRepository) {
        this.interviewService = interviewService;
        this.userRepository = userRepository;
    }

    // Get logged-in user
    private AppUser getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "User not found"));
    }

    // Get all interviews
    @GetMapping
    public List<Interview> getAllInterviews(
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);
        return interviewService.getAllInterviews(user.getId());
    }

    // Get interview by ID
    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterviewById(
            @PathVariable Long id,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        return interviewService.getInterviewById(id, user.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add interview
    @PostMapping
    public ResponseEntity<Interview> addInterview(
            @RequestBody Interview interview,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        Interview savedInterview =
                interviewService.addInterview(interview, user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedInterview);
    }

    // Update interview
    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
            @PathVariable Long id,
            @RequestBody Interview updatedInterview,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        return interviewService.updateInterview(
                        id, user.getId(), updatedInterview)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete interview
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(
            @PathVariable Long id,
            Authentication authentication) {

        AppUser user = getCurrentUser(authentication);

        boolean deleted =
                interviewService.deleteInterview(id, user.getId());

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}