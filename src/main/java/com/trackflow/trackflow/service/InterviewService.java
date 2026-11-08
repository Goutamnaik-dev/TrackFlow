package com.trackflow.trackflow.service;

import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.model.Interview;
import com.trackflow.trackflow.repository.InterviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    // Get interviews belonging to a user
    public List<Interview> getAllInterviews(Long userId) {
        return interviewRepository.findAllByUser_Id(userId);
    }

    // Get interview by ID and user ID
    public Optional<Interview> getInterviewById(Long id, Long userId) {
        return interviewRepository.findByIdAndUser_Id(id, userId);
    }

    // Add interview
    public Interview addInterview(Interview interview, AppUser user) {
        interview.setId(null);
        interview.setUser(user);
        return interviewRepository.save(interview);
    }

    // Update interview
    public Optional<Interview> updateInterview(
            Long id, Long userId, Interview updatedInterview) {

        return interviewRepository.findByIdAndUser_Id(id, userId)
                .map(existingInterview -> {

                    existingInterview.setCompany(updatedInterview.getCompany());
                    existingInterview.setJobTitle(updatedInterview.getJobTitle());
                    existingInterview.setInterviewRound(updatedInterview.getInterviewRound());
                    existingInterview.setScheduledAt(updatedInterview.getScheduledAt());
                    existingInterview.setInterviewMode(updatedInterview.getInterviewMode());
                    existingInterview.setMeetingLink(updatedInterview.getMeetingLink());
                    existingInterview.setLocation(updatedInterview.getLocation());
                    existingInterview.setRecruiterName(updatedInterview.getRecruiterName());
                    existingInterview.setRecruiterContact(updatedInterview.getRecruiterContact());
                    existingInterview.setPreparationNotes(updatedInterview.getPreparationNotes());
                    existingInterview.setStatus(updatedInterview.getStatus());

                    return interviewRepository.save(existingInterview);
                });
    }

    // Delete interview
    public boolean deleteInterview(Long id, Long userId) {

        if (!interviewRepository.existsByIdAndUser_Id(id, userId)) {
            return false;
        }

        interviewRepository.deleteById(id);
        return true;
    }
}