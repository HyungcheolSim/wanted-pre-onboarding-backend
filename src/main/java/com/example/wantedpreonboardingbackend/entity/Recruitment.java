package com.example.wantedpreonboardingbackend.entity;

import com.example.wantedpreonboardingbackend.dto.RecruitmentRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="recruitment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Recruitment extends Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruitment_id")
    private Long id;

    @Column(name="position",nullable = false,length = 32)
    private String position;

    @Column(name="reward")
    private Long reward;

    @Column(name="recruitment_introduction")
    private String introduction;

    @Column(name="stack",nullable = false)
    private String stack;

    @ManyToOne
    @JoinColumn(name="company_id",nullable = false)
    private Company company;

    public void update(RecruitmentRequest recruitmentRequest) {
        this.position=recruitmentRequest.getPosition();
        this.reward=recruitmentRequest.getReward();
        this.introduction=recruitmentRequest.getIntroduction();
        this.stack=recruitmentRequest.getStack();
    }
}
