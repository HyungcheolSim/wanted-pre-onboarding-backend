package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment,Long> {
    //    @Query(value = "select recruitment_id, created_date, last_modified_date, recruitment_introduction, position, reward, stack from Recruitment R left join company c on c.company_id = R.company",nativeQuery = true)
        List<Recruitment> findByIntroductionContains(String search);
    //회사명,국가,지역,사용기술,채용 포지션,...
}
