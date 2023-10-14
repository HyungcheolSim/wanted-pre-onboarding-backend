package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    @Query("SELECT distinct r From Recruitment r join fetch r.company WHERE CONCAT_WS('',r.introduction,r.position,r.stack,r.company.name,r.company.nation,r.company.introduction,r.company.region) Like %:keyword%")
    List<Recruitment> findAllWithCompanyUsingFetchJoin(@Param("keyword") String keyword);
}
