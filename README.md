# 최신 프로젝트 업데이트 (by 2026-07-29)
* kafka connect 실습을 위한 docker-compose 
* 구성 1 - mariadb 관련
  * mariadb-source 폴더: kafka source connect 테스트를 위한 mariadb (included users table)
  * mariadb-sink 폴더: kafka sink connect 테스트를 위한 mariadb
  * docker-compose-mariadb.yml: mariadb용 docker compose 파일
  * source-connector.json: source connect 생성 스크립트
  * sink-connector.json: sink connect 생성 스크립트
``` yaml
 실행 명령어) docker-compose -f docker-compose-mariadb.yml up -d
 종료 명령어) docker-compose -f docker-compose-mariadb.yml down -v
```
* 구성 2 - kafka + kafka-connect 관련
  * docker-compose.yml: kafka + kafka-compose용 docker compose 파일 
  * 실행 명령어) docker-compose  up -d
  * 종료 명령어) docker-compose  down -v
``` yaml
 실행 명령어) docker-compose  up -d
 종료 명령어) docker-compose  down -v
```