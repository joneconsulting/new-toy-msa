# Spring Cloud Bootcamp Assessment #1

# Spring Cloud 기반 MSA 구성 및 동적 환경설정

> **제출시간:** 2시간\
> **제출방법:** GitHub Public Repository URL 제출

------------------------------------------------------------------------

## 과제 목표

지금까지 학습한 Eureka, API Gateway, Spring Cloud Config Server,
RabbitMQ, Spring Cloud Bus를 활용하여 MSA 환경을 직접 구성하고, 중앙
환경설정 관리와 동적 설정 변경을 구현한다.

------------------------------------------------------------------------

## 사전 준비

-   자신의 GitHub 계정에 **Public Repository**를 생성한다.
-   Config Server에서 사용할 설정 파일은 **본인의 GitHub 저장소**에서
    관리한다.

예)

    https://github.com/<github-id>/spring-cloud-config

Config Repository 예시

    application.yml
    application-dev.yml
    user-service.yml
    user-service-dev.yml

------------------------------------------------------------------------

# 예시

## 예시 1. Config Server 설정

user-service.yml

``` yaml
message:
  greeting: Hello Spring Cloud
```

Controller

``` java
@GetMapping("/message")
public String message() {
    return greeting;
}
```

호출

    GET /users/message

결과

    Hello Spring Cloud

------------------------------------------------------------------------

## 예시 2. Spring Cloud Bus

Git 저장소 수정

``` yaml
message:
  greeting: Hello Spring Cloud Bus
```

Bus Refresh

    POST /actuator/busrefresh

서비스 재시작 없이

    GET /users/message

결과

    Hello Spring Cloud Bus

------------------------------------------------------------------------

# 수행해야 할 업무

## Step 1

다음 서비스를 모두 실행한다.

-   Eureka
-   Config Server
-   API Gateway
-   User Service
-   Order Service
-   Catalog Service
-   RabbitMQ(Docker)

### 확인

-   모든 서비스가 Eureka에 등록되는가?
-   Gateway를 통해 접근 가능한가?

------------------------------------------------------------------------

## Step 2

본인의 GitHub Public Repository에 Config 파일(파일명은 애플리케이션명에 맞게 본인이 설정)을 생성한다.

다음 값을 추가한다.

``` yaml
message:
  greeting: Hello Spring Cloud
```

User Service에

    GET /users/message

API를 구현한다.

------------------------------------------------------------------------

## Step 3

Git 저장소의 값을 변경한다.

``` yaml
message:
  greeting: Hello Spring Cloud Bus
```

Spring Cloud Bus를 이용하여

    POST /actuator/busrefresh

를 수행한다.

서비스를 재시작하지 않고 변경된 값이 출력되는지 확인한다.

------------------------------------------------------------------------

## Step 4

Gateway를 통해 아래 기능을 모두 테스트한다. (POSTMAN 사용)

-   회원가입
-   로그인
-   사용자 목록
-   상품 목록
-   주문 생성
-   환경설정 조회

------------------------------------------------------------------------

## Step 5

Git 저장소에 다음 설정을 추가한다.

``` yaml
company:
  name: NJONE Company
  ceo: Hong Gil Dong
```

다음 API를 구현한다.

    GET /users/company

예상 결과

``` json
{
  "name":"NJONE Company",
  "ceo":"Hong Gil Dong"
}
```

Bus Refresh 후 변경사항이 반영되는지 확인한다.

------------------------------------------------------------------------

# 제출 결과물

1.  GitHub Public Repository URL
2.  실행 화면 캡처
  -   Eureka Dashboard
  -   RabbitMQ Management
  -   Config Server
3.  Postman 실행 결과 (위에서 실행한 모든 Postman의 실행 결과)
4.  변경한 Config 파일
5.  구현한 Controller 및 Service 코드

------------------------------------------------------------------------

# 평가 기준

| 평가 항목                           | 평가 내용 | 배점 |
|---------------------------------|-----------|----:|
| Eureka 서비스 등록                   | 모든 서비스가 Eureka에 정상 등록되고 Dashboard에서 확인 가능 | 15점 |
| API Gateway 라우팅                 | Gateway를 통해 회원가입, 로그인, 상품조회, 주문 API가 정상 동작 | 20점 |
| Spring Cloud Config Server      | Config Server와 GitHub Repository를 정상 연동하고 설정을 조회 | 20점 |
| Spring Cloud Bus                | RabbitMQ와 Bus를 이용하여 서비스 재시작 없이 설정 변경 반영 | 20점 |
| Spring Cloud Config 테스트용 API 구현 | `/users/message`, `/users/company` API를 정상 구현 | 15점 |
| GitHub 및 제출물                    | GitHub Public Repository 구성, README 작성, 실행 화면 및 결과 제출 | 10점 |
| **총점**                          |  | **100점** |

------------------------------------------------------------------------

# Bonus (+10점)

다음 중 하나 이상 구현

-   application-dev.yml 적용
-   Gateway Filter 추가
-   Order Service에도 Config Server 적용
-   Config 우선순위 설명(README 작성)
