package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.ApplicationRequest;
import com.example.wantedpreonboardingbackend.dto.CommonResponse;
import com.example.wantedpreonboardingbackend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplicationController {
    private final ApplicationService applicationService;
    //지원
    @PostMapping("/application")
    public ResponseEntity<CommonResponse> applyRecuritment(@RequestBody ApplicationRequest applicationRequest){
        applicationService.applyRecruitment(applicationRequest);
        return ResponseEntity.ok().body(new CommonResponse("채용공고 지원 성공!",200));
    }
}
