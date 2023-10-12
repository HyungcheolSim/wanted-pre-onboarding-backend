package com.example.wantedpreonboardingbackend.dto;


import lombok.Getter;

@Getter
public class RecruitmentRequest {
    private Long companyId;
    private String position;
    private Long reward;
    private String introduction;
    private String stack;
}
