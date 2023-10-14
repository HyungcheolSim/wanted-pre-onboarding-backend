package com.example.wantedpreonboardingbackend;

import com.example.wantedpreonboardingbackend.dto.RecruitmentDetailResponse;
import com.example.wantedpreonboardingbackend.dto.RecruitmentRequest;
import com.example.wantedpreonboardingbackend.entity.Company;
import com.example.wantedpreonboardingbackend.entity.Recruitment;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.RecruitmentRepository;
import com.example.wantedpreonboardingbackend.service.RecruitmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@DisplayName("RecruitmentTest")
@ExtendWith(MockitoExtension.class)
public class RecruitmentTest {
    @InjectMocks
    private RecruitmentService recruitmentService;
    @Mock
    private RecruitmentRepository recruitmentRepository;
    @Mock
    private CompanyRepository companyRepository;

    @DisplayName("CREATE_RECRUITMENT_TEST")
    @Test
    public void recruitmentService_createRecruitment() {
        RecruitmentRequest recruitmentRequest = new RecruitmentRequest(1L, "백엔드 개발자_TEST", 1234123L, "테스트_회사1에서 백엔드 개발자 모집", "Java");
        Recruitment recruitment = Recruitment.builder()
                .position(recruitmentRequest.getPosition())
                .reward(recruitmentRequest.getReward())
                .introduction(recruitmentRequest.getIntroduction())
                .stack(recruitmentRequest.getStack())
                .company(new Company(1L,"테스트 컴퍼니","테스트 컴퍼니 소개","한국","서울",new ArrayList<>()))
                .build();

        when(recruitmentRepository.save(Mockito.any(Recruitment.class))).thenReturn(recruitment);
        RecruitmentDetailResponse recruitmentDetailResponse = recruitmentService.createRecruitment(recruitmentRequest);

        Assertions.assertThat(recruitmentDetailResponse.getReward()).isEqualTo(recruitmentRequest.getReward());
        Assertions.assertThat(recruitmentDetailResponse.getRecruitInfo()).isEqualTo(recruitmentRequest.getIntroduction());
        verify(companyRepository,times(1)).findById(any());
        verify(recruitmentRepository,times(1)).save(any(Recruitment.class));
    }
}
