
# Spring Security (JWT) Template

## 프로젝트 소개

이 프로젝트는 Spring Security와 JWT(Json Web Token)을 활용하여 보안 인증 및 권한 관리를 구현한 템플릿입니다.

## 프로젝트 기술 스택

- Spring Boot (version 3.2.4)
- Spring Web
- Spring Security
- MongoDB
- Redis
- JWT

## 프로젝트 설정

### application.yaml 설정
프로젝트를 사용하기 전에 `src/resources/application_template.yaml` 파일을 복사하여 `src/resources/application.yaml`로 변경해야 합니다. 그리고 아래의 내용을 수정합니다.
```yaml  
spring:  
 data: 
  mongodb:
    host: localhost
    port: 27017
    username: (username)
    password: (password)
    database: (database)
    authentication-database: admin
  redis:
    host: localhost
    port: 6379
    password: (password)  
app:  
 security:
  jwt-secret-key: (jwt_secret) 
  access-token-expirations-hour: 4
  refresh-token-expirations-hour: 168  
```  
괄호 안의 내용을 해당하는 값으로 변경해야 합니다.

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
    "username": "(username)",  
    "password": "(password)"
  }  
  ```  
- **Response Body:** 정상적으로 회원가입되면 별도의 응답 바디는 없습니다.

## 인증 및 인가
API 요청 시 `accessToken`은 `Request Header`의 `Authorization`에 "Bearer (Token)" 저장하고, `refreshToken`은 `Request Cookie`의 `refresh_token`에 저장하여주세요.
만약 응답이 왔을 때 `accessToken`이 만료되었다면, 다음과 같이 Response Header에 새로운 `accessToken`과 `refreshToken`이 전송됩니다.
- `App-Reissue-Token: 1`
- `App-New-Access-Token`: 새로운 `accessToken`
- `App-New-Refresh-Token`: 새로운 `refreshToken`
