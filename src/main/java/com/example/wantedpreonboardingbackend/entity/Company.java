package com.example.wantedpreonboardingbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long id;

	@Column(name = "company_name", nullable = false, length = 32)
	private String name;

	@Column(name = "company_introduction")
	private String introduction;

	@Column(name = "nation", nullable = false)
	private String nation;
	@Column(name = "region", nullable = false)
	private String region;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Recruitment> Recruitments;
}
