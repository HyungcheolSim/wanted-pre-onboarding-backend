package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.ApplicationRequest;
import com.example.wantedpreonboardingbackend.entity.Application;
import com.example.wantedpreonboardingbackend.entity.Member;
import com.example.wantedpreonboardingbackend.entity.Recruitment;
import com.example.wantedpreonboardingbackend.repository.ApplicationRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import com.example.wantedpreonboardingbackend.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void applyRecruitment(ApplicationRequest applicationRequest) {
        Recruitment recruitment=recruitmentRepository.findById(applicationRequest.getRecruitmentId()).orElseThrow(()->new IllegalArgumentException("채용공고가 존재하지 않습니다."));
        Member member= memberRepository.findById(applicationRequest.getMemberId()).orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
        if(applicationRepository.existsByMemberAndRecruitment(member,recruitment)){
            throw new IllegalArgumentException("이미 해당 채용공고에 지원하셨습니다.");
        }
        Application application=Application.builder().recruitment(recruitment).member(member).build();
        applicationRepository.save(application);
    }
}
