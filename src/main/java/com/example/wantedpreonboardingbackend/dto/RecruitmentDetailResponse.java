package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class RecruitmentDetailResponse extends RecruitmentResponse{
    private String recruitInfo;
    private List<Long> recruitmentIds;

    public RecruitmentDetailResponse(Recruitment recruitment) {
        super(recruitment);
        this.recruitInfo=recruitment.getIntroduction();
        this.recruitmentIds=recruitment.getCompany().getRecruitments().stream().map(Recruitment::getId).filter(id-> !Objects.equals(id, recruitment.getId())).toList();
    }

}
