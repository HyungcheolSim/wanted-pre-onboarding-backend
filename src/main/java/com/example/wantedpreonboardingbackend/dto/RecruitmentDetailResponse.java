package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitmentDetailResponse extends RecruitmentResponse{
    private String recruitInfo;
    private List<Long> recruitmentIds;

    public RecruitmentDetailResponse(Recruitment recruitment) {
        super(recruitment);
        this.recruitInfo=recruitment.getIntroduction();
        this.recruitmentIds=recruitment.getCompany().getRecruitments().stream().filter(rec->rec.getId()!=recruitment.getId()).map(Recruitment::getId).toList();


        // 회사가 올린 다른 채용공고 목록private List<>
    }

}
