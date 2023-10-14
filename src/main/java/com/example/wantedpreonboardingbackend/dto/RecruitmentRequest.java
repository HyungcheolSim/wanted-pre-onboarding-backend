package com.example.wantedpreonboardingbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitmentRequest {
    private Long companyId;
    private String position;
    private Long reward;
    private String introduction;
    private String stack;
}
