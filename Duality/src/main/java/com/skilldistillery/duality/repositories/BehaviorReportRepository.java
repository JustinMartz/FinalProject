package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.BehaviorReport;

public interface BehaviorReportRepository extends JpaRepository<BehaviorReport, Integer>  {

}
