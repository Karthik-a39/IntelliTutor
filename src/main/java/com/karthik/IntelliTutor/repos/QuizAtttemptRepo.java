package com.karthik.IntelliTutor.repos;

import com.karthik.IntelliTutor.entities.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAtttemptRepo extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUserEmail(String email);

}