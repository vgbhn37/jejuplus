![image](https://github.com/vgbhn37/jejuplus/assets/125956057/e11c9d56-370a-407a-a085-c2f53341b745)
<br>
## 🚀 프로젝트 개요
- 모든 팀원이 풀스택으로 개발에 참여했으며, 각자의 역활은 아래와 같습니다.


     | Name | 임지훈 | 김선영 | 강중현 | 남형빈 |
     | ------------ | ------------- | ------------- | ------------- | ------------- |
     | Position | 팀장 & 스케줄  | 여행 관련  | 항공권 관련 | 회원 관련,관리자 |
- 프로젝트 기간 : 2023년 10월 23일 ~ 2023년 11월 14일
- 시연 영상 (유튜브):

## 🎮 기술 스택
### ✨ Front-End
<details>
    <summary>⚡️ 자세히 살펴보기</summary>
    <br>
    <ul>
        <li>bootstrap </li>
        <li>HTML5 </li>
        <li>CSS3 </li>
        <li>JavaScript </li>
        <li>JQuery </li>
    </ul>
</details>
        <br>
        
### 💻 Back-End

<details>
      <summary>⚡️ 자세히 살펴보기</summary>
      <br>
      <ul>
          <li>springboot  </li>
          <li>H2 Database → MySQL </li>
          <li>jdk  </li>
          <li>lombok </li>
          <li>MyBatis </li>
          <li>JSP </li>
          <li>Spring Security(PasswordEncoder) </li>
          <li>JavaMailSender </li>
          <li>EnableScheduling</li>
          <li>Apache Tomcat </li>              
      </ul>
  </details>
  
  <br>

### 🛠 외부 API

<details>
      <summary>⚡️ 자세히 살펴보기</summary>
      <br>
      <ul>
          <li>카카오 소셜 로그인 API</li>
          <li>이메일 전송 API (구글 SMTP 프로토콜)</li>
          <li>카카오 맵 API</li>
          <li>항공편 api</li>
          <li>날씨 api</li>        
      </ul>
</details>

<br>

### 🙌🏻 Collaboration
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=Jira&logoColor=white"/> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=Slack&logoColor=white"/> <img src="https://img.shields.io/badge/Github-181717?style=flat&logo=Github&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Eclipse-2C2255?style=flat&logo=Notion&logoColor=white"/>  <br><img src="https://img.shields.io/badge/JQuery-0769AD?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=Notion&logoColor=white"/>

## ⚙ 의존성
    implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.1'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
    implementation 'javax.servlet:jstl'
    implementation 'com.googlecode.json-simple:json-simple:1.1.1'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.1'
	
	implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-mail'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    
    implementation 'org.springframework.security:spring-security-crypto'

## 1️⃣ 프로젝트 구조

    📦src
     ┗ 📂main
       ┣ 📂java
       ┃ ┗ 📂com
       ┃   ┗ 📂green
       ┃     ┗ 📂jejuplus
       ┃       ┃ ┣ 📂config
       ┃       ┃ ┣ 📂controller
       ┃       ┃   ┣ 📂admin
       ┃       ┃   ┣ 📂air
       ┃       ┃   ┣ 📂contents
       ┃       ┃   ┣ 📂schedule
       ┃       ┃   ┣ 📂user
       ┃       ┃   ┗ 📑 MainController.java
       ┃       ┃ ┣ 📂dto
       ┃       ┃   ┣ 📂admin
       ┃       ┃   ┣ 📂air
       ┃       ┃   ┣ 📂contents
       ┃       ┃   ┣ 📂schedule
       ┃       ┃   ┣ 📂user
       ┃       ┃   ┣ 📑 MainRestaurantDto.java
       ┃       ┃   ┗ 📑 SingleResponseDto.java       
       ┃       ┃ ┣ 📂handler
       ┃       ┃   ┣ 📂exception
       ┃       ┃   ┣ 📑 MyPageExceptionHandler.java
       ┃       ┃   ┗ 📑 MyUserExceptionHandler.java  
       ┃       ┃ ┣ 📂repository
       ┃       ┃   ┣ 📂interfaces
       ┃       ┃   ┗ 📂model      
       ┃       ┃ ┣ 📂service
       ┃       ┃   ┣ 📂admin
       ┃       ┃   ┣ 📂air
       ┃       ┃   ┣ 📂contents
       ┃       ┃   ┣ 📂payment
       ┃       ┃   ┣ 📂schedule
       ┃       ┃   ┣ 📂user
       ┃       ┃   ┗ 📑 MainService.java 
       ┃       ┃ ┣ 📂util
       ┃       ┃ ┗ 📑JejuplusApplication.java
       ┣ 📂resources
       ┃  ┣ 📂mapper
       ┃  ┗ 📂static
       ┃    ┗ 📂css
       ┃    ┃  ┣ 📂air
       ┃    ┃  ┣ 📂contents
       ┃    ┃  ┣ 📂schedule
       ┃    ┃  ┗ 📂user
       ┃    ┣ 📂fonts
       ┃    ┣ 📂images
       ┃    ┃  ┗ 📂air
       ┃    ┃  ┣ 📂icon
       ┃    ┃  ┣ 📂kakao
       ┃    ┃  ┣ 📂promotion
       ┃    ┃  ┣ 📂schedule
       ┃    ┃  ┣ 📂like
       ┃    ┣ 📂js
       ┃    ┃  ┣ 📂admin
       ┃    ┃  ┣ 📂air
       ┃    ┃  ┣ 📂contents
       ┃    ┃  ┣ 📂schedule
       ┃    ┃  ┗ 📂user
       ┃    ┣ 📂media
       ┃    ┗ 📂vendor
       ┗ 📂webapp
          ┗ 📂WEB-INF
            ┗ 📂view
              ┣  📂admin
              ┣ 📂air
              ┣ 📂contents
              ┣ 📂error
              ┣ 📂payment
              ┣ 📂schedule
              ┣ 📂user
              ┣ 📑footer.jsp
              ┣ 📑header.jsp
              ┗ 📑main.jsp    
</details>
    
<br>

    

## 2️⃣ 프로젝트 개요

- 실무에서도 활용 가능하며 여러 기능을 포함시켜 각자 기존에 맡아보지 않았던 기능들이 포함된 것들 중,
  예약,결제,환불,외부 API, 내부 API 또한 활용할 수 있던 여행 일정 사이트로 주제 선정
- 벤치 마킹(트리플,비짓 제주)

## 3️⃣ 기능 구분
### Member
- 소셜 로그인 API, 구글 이메일 SMTP 프로토콜
- 항공권 예매 기능, 결제 및 환불 API
- 제주도 날씨 기능, 달력 기능
- 스케줄 생성 기능,스케줄에 추가한 장소 거리에 맞게 정렬 기능
- 카카오 맵 API, 제주 명소 리스트, 명소 별 추천 및 댓글 기능
- 광고,명소 등등 상세보기
<br>

### Manager
- 회원 조회 및 삭제
- 광고 조회 및 삭제
- 광고 추가 및 수정

## 4️⃣ ERD & 테이블 명세서
![image](https://github.com/vgbhn37/jejuplus/assets/125956057/541ba7e5-88a9-4689-9d46-bf62a43b455d)


## 5️⃣ SiteMap

#### User
![image](https://github.com/vgbhn37/jejuplus/assets/125956057/1f386cc2-a5f1-4e6f-b29f-0d787f47f34f)

<br>

#### Admin
![image](https://github.com/vgbhn37/jejuplus/assets/125956057/2051cffd-bfcc-4327-8d25-6411c3611451)



## 6️⃣ 주요 기능


## 7️⃣ 기능 - 회원

#### 회원가입
- 아이디 중복 확인,글자 수 제한
- 비밀번호 확인
- 이메일 중복 확인, 인증 코드 발급 후 확인
- 유효성 확인

#### 로그인
- 카카오 소셜 로그인
- 일반 로그인
- 아이디 찾기    
- 비밀번호 찾기

#### 아이디 찾기
- 아이디의 가입한 이메일과 일치 여부 확인
- 일치 시 이메일로 아이디 전송


#### 비밀번호 찾기
- 아이디의 가입한 이메일과 일치 여부 확인
- 일치 시 인증 번호 전송 후 확인
- 인증 후 이메일로 임시 비밀번호 발급

#### 내정보 수정
- 회원정보 수정
- 이메일 변경
  - 중복여부 확인 후 인증 코드 확인
- 비밀번호 변경
  - 현재 비밀번호와 일치한지 확인 후 변경

#### 회원 탈퇴
- 아이디,비밀번호 일치 여부 확인
- 일치 시 탈퇴

#### 광고 상세보기
- 광고에 대한 정보들 출력(제목,소개글,이미지,내용)
 
  
## 8️⃣ 기능 - 관리자


#### 회원 관리
- 가입된 회원들 정보 리스트로 출력(페이징 처리)
- 카테고리(모두,아이디,이메일,이름,전화번호)별로 검색 기능
- 회원 탈퇴 버튼 
- 계정 권한 변경 버튼 ( 모달창(일반,관리자,VIP) )


#### 광고 관리
- 올린 광고들 정보 리스트로 출력(페이징처리)
- 광고 올릴 업체이름으로 검색 기능
- 광고 한달 연장 버튼 
- 광고 삭제 버튼 
- 광고 수정 페이지 연결 기능
- 광고 상세보기 페이지 연결 기능

#### 광고 추가
- 제목,소개,내용,이미지(3개) 추가 
- 추가 시 올린 시점 기준으로 종료 날짜(한달 뒤) 생성 
- 처음 작성이라 이미지 폴더 없을시 설정해둔 위치로 폴더 자동 생성

#### 광고 수정
- 작성했던 제목,소개,내용,이미지들 출력
- 작성했던 제목,소개,내용,이미지들 수정 기능
  
