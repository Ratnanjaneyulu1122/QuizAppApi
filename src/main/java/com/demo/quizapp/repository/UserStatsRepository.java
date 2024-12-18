package com.demo.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.quizapp.entity.UserStats;

public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
	UserStats findByUserId(String userId);
}
