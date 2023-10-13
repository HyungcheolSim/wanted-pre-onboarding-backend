package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class RecruitmentResponse {
    private Long id;
    private String companyName;
    private String position;
    private Long reward;
    private String stack;
    private String nation;
    private String region;


    public RecruitmentResponse(Recruitment recruitment){
        this.id=recruitment.getId();
        this.companyName=recruitment.getCompany().getName();
        this.position=recruitment.getPosition();
        this.reward=recruitment.getReward();
        this.stack=recruitment.getStack();
        this.nation=recruitment.getCompany().getNation();
        this.region= recruitment.getCompany().getRegion();

    }
}
