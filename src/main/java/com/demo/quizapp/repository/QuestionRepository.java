package com.demo.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.quizapp.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
