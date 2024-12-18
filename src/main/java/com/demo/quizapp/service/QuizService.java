package com.demo.quizapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.quizapp.entity.Question;
import com.demo.quizapp.entity.QuizSession;
import com.demo.quizapp.entity.UserStats;
import com.demo.quizapp.repository.QuestionRepository;
import com.demo.quizapp.repository.QuizSessionRepository;
import com.demo.quizapp.repository.UserStatsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuizSessionRepository quizSessionRepository;

	@Autowired
	private UserStatsRepository userStatsRepository;

	public QuizSession startQuiz(String userId) {
		QuizSession session = quizSessionRepository.findByUserId(userId);
		if (session == null) {
			session = new QuizSession();
			session.setUserId(userId);
			quizSessionRepository.save(session);
		}
		return session;
	}

	public Optional<Question> getRandomQuestion(String userId) {
		QuizSession session = quizSessionRepository.findByUserId(userId);
		if (session == null)
			return Optional.empty();

		return questionRepository.findAll().stream().filter(q -> !session.getAnsweredQuestions().contains(q.getId()))
				.findAny();
	}

	public boolean submitAnswer(String userId, Long questionId, String answer) {
		// Validate and fetch the question
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));

		System.out.println("Correct Answer: " + question.getCorrectAnswer());
		System.out.println("Submitted Answer: " + answer);

		// Validate and fetch the quiz session
		QuizSession session = quizSessionRepository.findByUserId(userId);
		if (session == null) {
			throw new RuntimeException("Quiz session not found for user ID: " + userId);
		}

		// Fetch or initialize user stats
		UserStats stats = userStatsRepository.findByUserId(userId);
		if (stats == null) {
			stats = new UserStats();
			stats.setUserId(userId);
			stats.setTotalAnswered(0);
			stats.setCorrectAnswers(0);
			stats.setIncorrectAnswers(0);
			userStatsRepository.save(stats);
		}

		// Update session with answered question
		if (!session.getAnsweredQuestions().contains(questionId)) {
			session.getAnsweredQuestions().add(questionId);
			quizSessionRepository.save(session);
		} else {
			System.out.println("Question ID: " + questionId + " has already been answered.");
		}

		// Update user stats
		stats.setTotalAnswered(stats.getTotalAnswered() + 1);
		if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
			stats.setCorrectAnswers(stats.getCorrectAnswers() + 1);
		} else {
			stats.setIncorrectAnswers(stats.getIncorrectAnswers() + 1);
		}
		userStatsRepository.save(stats);

		System.out.println("Answer submission successful for user ID: " + userId);
		return true;
	}

	public UserStats getUserStats(String userId) {
		return userStatsRepository.findByUserId(userId);
	}
}
