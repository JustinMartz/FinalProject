package com.skilldistillery.duality.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.BehaviorReport;
import com.skilldistillery.duality.entities.User;

public interface BehaviorReportRepository extends JpaRepository<BehaviorReport, Integer>  {
	List<BehaviorReport> findByCreateDate(LocalDateTime time);
	List<BehaviorReport> findByUserAndCreateDateBetween(User user, LocalDateTime first, LocalDateTime last);
	List<BehaviorReport> findByUserAndCreateDateBetweenOrderByCreateDateDesc(User user, LocalDateTime first, LocalDateTime last);
}
