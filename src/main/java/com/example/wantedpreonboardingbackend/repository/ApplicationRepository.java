package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Application;
import com.example.wantedpreonboardingbackend.entity.Member;
import com.example.wantedpreonboardingbackend.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    Boolean existsByMemberAndRecruitment(Member member, Recruitment recruitment);
}
