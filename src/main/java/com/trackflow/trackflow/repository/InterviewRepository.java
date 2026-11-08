package com.trackflow.trackflow.repository;

import com.trackflow.trackflow.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository
        extends JpaRepository<Interview, Long> {

    List<Interview> findAllByUser_Id(Long userId);

    Optional<Interview> findByIdAndUser_Id(Long id, Long userId);

    boolean existsByIdAndUser_Id(Long id, Long userId);
}