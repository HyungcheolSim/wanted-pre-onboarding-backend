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
    public ResponseEntity<CommonResponse> getRecruitments() {
        //return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공",200, search==null?recruitmentService.getRecruitments():recruitmentService.getRecruitmentBySearchKeyword(search)));
        return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공", 200, recruitmentService.getRecruitments()));
    }

    @GetMapping("/recruitment")
    public ResponseEntity<CommonResponse> getRecruitmentSearch(@RequestParam(required = false) String search) {
//        List<RecruitmentResponse> result;
//        if (search == null) {
//            result = recruitmentService.getRecruitments();
//        } else {
//            result = recruitmentService.getRecruitmentBySearchKeyword(search);
//        }
        return ResponseEntity.ok().body(new CommonResponse("채용 공고 검색 성공", 200, search==null? recruitmentService.getRecruitments(): recruitmentService.getRecruitmentBySearchKeyword(search)));
    }

    @GetMapping("/recruitments/{id}")
    public ResponseEntity<CommonResponse> getRecruitment(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공", 200, recruitmentService.getRecruitment(id)));
    }

    @PostMapping("/recruitment")
    public ResponseEntity<CommonResponse> createRecruitment(@RequestBody RecruitmentRequest recruitmentRequest) {

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponse("채용 공고 생성 성공", 201, recruitmentService.createRecruitment(recruitmentRequest)));
    }

    @PutMapping("/recruitments/{id}")
    public ResponseEntity<CommonResponse> updateRecruitment(@PathVariable Long id, @RequestBody RecruitmentRequest recruitmentRequest) {

        return ResponseEntity.ok().body(new CommonResponse("채용 공고 수정 성공", 200, recruitmentService.updateRecruitment(id, recruitmentRequest)));
    }

    @DeleteMapping("/recruitments/{id}")
    public ResponseEntity<CommonResponse> deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
        return ResponseEntity.ok().body(new CommonResponse("채용 공고 삭제 성공", 200));
    }
}
