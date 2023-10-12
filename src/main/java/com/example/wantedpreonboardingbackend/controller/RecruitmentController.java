package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.CommonResponse;
import com.example.wantedpreonboardingbackend.dto.RecruitmentRequest;
import com.example.wantedpreonboardingbackend.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @GetMapping("/recruitments")
    public ResponseEntity<CommonResponse> getRecruitments(){
        return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공",200,recruitmentService.getRecruitments()));
    }

    @PostMapping("/recruitment")
    public ResponseEntity<CommonResponse> createRecruitment(@RequestBody RecruitmentRequest recruitmentRequest){

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponse("채용 공고 생성 성공",201,recruitmentService.createRecruitment(recruitmentRequest)));
    }
}
