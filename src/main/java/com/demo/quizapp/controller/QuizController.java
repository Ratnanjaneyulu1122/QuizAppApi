package com.demo.quizapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.quizapp.entity.Question;
import com.demo.quizapp.entity.QuizSession;
import com.demo.quizapp.entity.UserStats;
import com.demo.quizapp.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@PostMapping("/start")
	public ResponseEntity<QuizSession> startQuiz(@RequestParam String userId) {
		return ResponseEntity.ok(quizService.startQuiz(userId));
	}

	@GetMapping("/question")
	public ResponseEntity<Question> getRandomQuestion(@RequestParam String userId) {
		Optional<Question> question = quizService.getRandomQuestion(userId);
		return question.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
	}

	@PostMapping("/submit")
	public ResponseEntity<String> submitAnswer(@RequestParam String userId, @RequestParam Long questionId,
			@RequestParam String answer) {
		boolean success = quizService.submitAnswer(userId, questionId, answer);
		return success ? ResponseEntity.ok("Answer submitted") : ResponseEntity.badRequest().body("Invalid submission");
	}

	@GetMapping("/results")
	public ResponseEntity<UserStats> getUserStats(@RequestParam String userId) {
		return ResponseEntity.ok(quizService.getUserStats(userId));
	}
}
