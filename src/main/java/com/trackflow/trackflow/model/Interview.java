package com.trackflow.trackflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(nullable = false, length = 150)
    private String company;

    @Column(name = "job_title", nullable = false, length = 150)
    private String jobTitle;

    @Column(name = "interview_round", nullable = false, length = 100)
    private String interviewRound;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "interview_mode", nullable = false, length = 30)
    private String interviewMode;

    @Column(name = "meeting_link", length = 500)
    private String meetingLink;

    @Column(length = 255)
    private String location;

    @Column(name = "recruiter_name", length = 150)
    private String recruiterName;

    @Column(name = "recruiter_contact", length = 150)
    private String recruiterContact;

    @Column(name = "preparation_notes", columnDefinition = "TEXT")
    private String preparationNotes;

    @Column(nullable = false, length = 30)
    private String status = "UPCOMING";

    public Interview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInterviewRound() {
        return interviewRound;
    }

    public void setInterviewRound(String interviewRound) {
        this.interviewRound = interviewRound;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public String getInterviewMode() {
        return interviewMode;
    }

    public void setInterviewMode(String interviewMode) {
        this.interviewMode = interviewMode;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public String getRecruiterContact() {
        return recruiterContact;
    }

    public void setRecruiterContact(String recruiterContact) {
        this.recruiterContact = recruiterContact;
    }

    public String getPreparationNotes() {
        return preparationNotes;
    }

    public void setPreparationNotes(String preparationNotes) {
        this.preparationNotes = preparationNotes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
