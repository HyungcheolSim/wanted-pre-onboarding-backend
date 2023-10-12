package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.RecruitmentDetailResponse;
import com.example.wantedpreonboardingbackend.dto.RecruitmentRequest;
import com.example.wantedpreonboardingbackend.dto.RecruitmentResponse;
import com.example.wantedpreonboardingbackend.entity.Recruitment;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<RecruitmentResponse> getRecruitments() {
        return recruitmentRepository.findAll().stream().map(RecruitmentResponse::new).toList();
    }

    @Transactional
    public RecruitmentDetailResponse createRecruitment(RecruitmentRequest recruitmentRequest) {

        Recruitment recruitment= Recruitment.builder()
                .position(recruitmentRequest.getPosition())
                .reward(recruitmentRequest.getReward())
                .introduction(recruitmentRequest.getIntroduction())
                .stack(recruitmentRequest.getStack())
                .company(companyRepository.findById(recruitmentRequest.getCompanyId()).orElseThrow(()->new IllegalArgumentException("해당 기업이 존재하지 않습니다.")))
                .build();
        return new RecruitmentDetailResponse(recruitmentRepository.save(recruitment));
    }
    @Transactional
    public RecruitmentDetailResponse updateRecruitment(Long id,RecruitmentRequest recruitmentRequest){
        Recruitment recruitment= findRecruitment(id);
        recruitment.update(recruitmentRequest);
        return new RecruitmentDetailResponse(recruitment);
    }
    public void deleteRecruitment(Long id){
        Recruitment recruitment=findRecruitment(id);
        recruitmentRepository.delete(recruitment);
    }
    private Recruitment findRecruitment(Long id){
        return recruitmentRepository.findById((id)).orElseThrow(()->new IllegalArgumentException("채용공고가 존재하지 않습니다."));
    }
}
