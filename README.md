
# Spring Security Template (MongoDB + JWT)

## 프로젝트 소개

이 프로젝트는 Spring Security와 JWT(Json Web Token)을 활용하여 보안 인증 및 권한 관리를 구현한 템플릿입니다.

## 프로젝트 기술 스택

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Boot_(v.%203.2.4)-F2F4F9?style=for-the-badge&logo=spring-boot">
<img src="https://img.shields.io/badge/Spring_Security-F2F4F9?style=for-the-badge&logo=springsecurity">
<img src="https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">

## API 명세서

### 로그인
- **Method:** POST
- **Endpoint:** `/api/user`
- **Request Body:**
  ```json  
  {  
    "username": "(username)",  
    "password": "(password)"  
  }  
  ```  
- **Response Body:** 정상적으로 로그인되면 `accessToken`과 `refreshToken`이 응답으로 반환됩니다.

### 회원가입
- **Method:** PUT
- **Endpoint:** `/api/user`
- **Request Body:**
  ```json  
  {  
    "email": "(email)",
    "password": "(password)"
  }  
  ```  
- **Response Body:** 정상적으로 회원가입되면 별도의 응답 바디는 없습니다.
