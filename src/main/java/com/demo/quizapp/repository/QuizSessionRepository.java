package com.demo.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.quizapp.entity.QuizSession;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
	QuizSession findByUserId(String userId);
}
