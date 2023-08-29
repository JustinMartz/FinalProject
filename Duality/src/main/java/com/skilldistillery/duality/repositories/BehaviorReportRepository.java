package com.skilldistillery.duality.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.BehaviorReport;

public interface BehaviorReportRepository extends JpaRepository<BehaviorReport, Integer>  {
	List<BehaviorReport> findByCreateDate(LocalDateTime time);
}
