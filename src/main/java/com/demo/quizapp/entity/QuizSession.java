package com.demo.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "quiz_sessions")
public class QuizSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userId;

	@ElementCollection
	private Set<Long> answeredQuestions = new HashSet<>();

}
