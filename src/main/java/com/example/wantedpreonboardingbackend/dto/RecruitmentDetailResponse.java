package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitmentDetailResponse extends RecruitmentResponse{
    private String recruitInfo;

    public RecruitmentDetailResponse(Recruitment recruitment) {
        super(recruitment);
        this.recruitInfo=recruitment.getIntroduction();
        // 회사가 올린 다른 채용공고 목록private List<>
    }

}
