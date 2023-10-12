package com.example.wantedpreonboardingbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name="company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long id;

    @Column(name="company_name",nullable = false,length = 32)
    private String name;

    @Column(name="company_introduction")
    private String introduction;

    @Column(name="nation",nullable = false)
    private String nation;
    @Column(name="area",nullable = false)
    private String area;

//    @OneToMany(mappedBy = "recruitment")
//    privat
}
