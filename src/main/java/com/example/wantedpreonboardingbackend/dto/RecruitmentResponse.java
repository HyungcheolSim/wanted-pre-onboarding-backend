package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class RecruitmentResponse {
    private Long companyId;
    private String companyName;
    private String position;
    private Long reward;
    private String stack;

    public RecruitmentResponse(Recruitment recruitment){
        this.companyId=recruitment.getCompany().getId();
        this.companyName=recruitment.getCompany().getName();
        this.position=recruitment.getPosition();
        this.reward=recruitment.getReward();
        this.stack=recruitment.getStack();
    }
}
