# wanted-pre-onboarding-backend

### 커밋 메세지 컨벤션
[Udacity Git Commit Message Style Guide](https://udacity.github.io/git-styleguide/)
### 코드 컨벤션
*네이버 코드 컨벤션*

![img_1.png](img_1.png)


## 서비스 개요
기업의 채용을 위한 웹 서비스
**회사**는 **채용공고**를 생성하고, 이에 **사용자**는 **지원**합니다.

### 엔티티
**회사, 채용공고, 사용자, 지원**

### ERD
![img.png](img.png)

## 요구사항
### 1. 회사는 채용 공고를 등록
채용공고에 필요한 정보들을 RequestBody로 DTO에 담는다. </p>
담은 정보를 바탕으로 Recruitment 객체 생성하고 Recruitment Table 에 저장</p>

*RecruitmentController*
```java
@PostMapping("/recruitment")
public ResponseEntity<CommonResponse> createRecruitment(@RequestBody RecruitmentRequest recruitmentRequest) {
    return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponse("채용 공고 생성 성공", 201, recruitmentService.createRecruitment(recruitmentRequest)));
}
```
*RecruitmentService*
```java
@Transactional
public RecruitmentDetailResponse createRecruitment(RecruitmentRequest recruitmentRequest) {
  Recruitment recruitment = Recruitment.builder()
  .position(recruitmentRequest.getPosition())
  .reward(recruitmentRequest.getReward())
  .introduction(recruitmentRequest.getIntroduction())
  .stack(recruitmentRequest.getStack())
  .company(companyRepository.findById(recruitmentRequest.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("해당 기업이 존재하지 않습니다.")))
  .build();
  return new RecruitmentDetailResponse(recruitmentRepository.save(recruitment));
}
```
### 2. 회사는 채용 공고를 수정
채용공고에서 회사ID를 제외한 정보 수정 가능</p>

*RecruitmentController*
```java
@PutMapping("/recruitments/{id}")
public ResponseEntity<CommonResponse> updateRecruitment(@PathVariable Long id, @RequestBody RecruitmentRequest recruitmentRequest) {
    return ResponseEntity.ok().body(new CommonResponse("채용 공고 수정 성공", 200, recruitmentService.updateRecruitment(id, recruitmentRequest)));
}
```
*RecruitmentService*
```java
@Transactional
public RecruitmentDetailResponse updateRecruitment(Long id, RecruitmentRequest recruitmentRequest) {
  Recruitment recruitment = findRecruitment(id);
  recruitment.update(recruitmentRequest);
  return new RecruitmentDetailResponse(recruitment);
}
```
### 3. 회사는 채용 공고를 삭제
채용공고 DB에서 삭제</p>

*RecruitmentController*
```java
@DeleteMapping("/recruitments/{id}")
public ResponseEntity<CommonResponse> deleteRecruitment(@PathVariable Long id) {
    recruitmentService.deleteRecruitment(id);
    return ResponseEntity.ok().body(new CommonResponse("채용 공고 삭제 성공", 200));
}
```
*RecruitmentService*
```java
@Transactional
public void deleteRecruitment(Long id) {
  Recruitment recruitment = findRecruitment(id);
  recruitmentRepository.delete(recruitment);
}
```
### 4-1. 채용 공고 전체 목록 조회, 4-2. 사용자는 채용공고 검색 가능
사용자가 확인할 수 있는 채용공고 목록 조회</p>
search에 해당하는 검색어가 없는 경우 전체조회가 실행
search에 해당하는 검색어가 있는 경우 해당 키워드를 Company와 Recuritment의 Column들 전체에서 포함할 경우 반환

*RecruitmentController*
```java
@GetMapping("/recruitments")
public ResponseEntity<CommonResponse> getRecruitmentSearch(@RequestParam(required = false) String search) {
  return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공", 200, search == null ? recruitmentService.getRecruitments() : recruitmentService.getRecruitmentsWithCompanyIncludeKeyword(search)));
}
```    
*RecruitmentService*
```java
//전체 조회
@Transactional(readOnly = true)
public List<RecruitmentResponse> getRecruitments() {
  return recruitmentRepository.findAll().stream().map(RecruitmentResponse::new).toList();
}
//키워드 검색 조회
@Transactional(readOnly = true)
public List<RecruitmentResponse> getRecruitmentsWithCompanyIncludeKeyword(String keyword) {
  return recruitmentRepository.findAllWithCompanyUsingFetchJoin(keyword).stream().map(RecruitmentResponse::new).toList();
}
```
*RecruitmentRepository*
```java
//여러 컬럼에서 Like 조회하기
@Query("SELECT distinct r From Recruitment r join fetch r.company WHERE CONCAT_WS('',r.introduction,r.position,r.stack,r.company.name,r.company.nation,r.company.introduction,r.company.region) Like %:keyword%")
    List<Recruitment> findAllWithCompanyUsingFetchJoin(@Param("keyword") String keyword);
```
### 5. 채용 상세 페이지
채용 내용이 추가적으로 담겨 있음</p>
해당 회사가 올린 **현재 조회하고 있는 공고를 제외한** 다른 채용 공고 목록 추가

*RecruitmentController*
```java
@GetMapping("/recruitments/{id}")
public ResponseEntity<CommonResponse> getRecruitment(@PathVariable Long id) {
  return ResponseEntity.ok().body(new CommonResponse("채용 공고 목록 조회 성공", 200, recruitmentService.getRecruitment(id)));
}
```
*RecruitmentService*
```java
@Transactional(readOnly = true)
public RecruitmentDetailResponse getRecruitment(Long id) {
  return new RecruitmentDetailResponse(findRecruitment(id));
}
```
*RecruitmentDetailResponse*
```java
@Getter
@NoArgsConstructor
public class RecruitmentDetailResponse extends RecruitmentResponse {
  private String recruitInfo;
  private List<Long> recruitmentIds;

  public RecruitmentDetailResponse(Recruitment recruitment) {
    super(recruitment);
    this.recruitInfo = recruitment.getIntroduction();
	//현재 조회하고 있는 채용공고를 제외한 채용 기업의 채용공고 ID 목록
    this.recruitmentIds = recruitment.getCompany().getRecruitments().stream().map(Recruitment::getId).filter(id -> !Objects.equals(id, recruitment.getId())).toList();
  }

}
```
### 6. 사용자는 채용공고에 지원
사용자는 1회만 지원 가능

*ApplicationController*
```java
@PostMapping("/application")
public ResponseEntity<CommonResponse> applyRecruitment(@RequestBody ApplicationRequest applicationRequest) {
  applicationService.applyRecruitment(applicationRequest);
  return ResponseEntity.ok().body(new CommonResponse("채용공고 지원 성공!", 200));
}
```
*ApplicationService*
```java
@Transactional
public void applyRecruitment(ApplicationRequest applicationRequest) {
  Recruitment recruitment=recruitmentRepository.findById(applicationRequest.getRecruitmentId()).orElseThrow(()->new IllegalArgumentException("채용공고가 존재하지 않습니다."));
  Member member= memberRepository.findById(applicationRequest.getMemberId()).orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
  if(applicationRepository.existsByMemberAndRecruitment(member,recruitment)){
    throw new IllegalArgumentException("이미 해당 채용공고에 지원하셨습니다.");
  }
  Application application=Application.builder().recruitment(recruitment).member(member).build();
  applicationRepository.save(application);
}
```

## 요구사항 분석
 - 채용공고 CRUD
 - 지원 C
 - 채용공고에 대한 여러 컬럼에서의 sql 질의를 통한 키워드 조회 
 - 예시 데이터들의 형태를 보며 테이블 별로 어떤 컬럼을 사용할지 결정

## 구현 과정
1. Entity 정의
2. DB 설계, 연결
3. Entity 작성
4. Repository 생성
5. Controller-> Service -> 필요한 DTO 생성 하며 각각의 기능 구현 

## 필수 기술요건

- ORM 사용하여 구현.
- RDBMS 사용 (SQLite, PostgreSQL,MySql 등).

## 평가 요소

- 요구사항 구현정도
    - 모든 기능을 구현하지 않더라도 평가를 진행합니다.
- 모델링
- 코드 가독성 및 코드 컨벤션


## 기술점수 가산점 요소
**(제출시기 가산점과 달리 기술점수 5점 이내 포함되는 가산점 입니다.)**
- [X] 가산점이 포함된 요구사항 해결(요구사항 5~6)
- [ ] Unit Test 구현
- [X] README 에 요구사항 분석 및 구현 과정을 작성
- [X] Git commit 메시지 컨벤션

