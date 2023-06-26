# sideproject_backside

backside

## application.properties

```java
spring.datasource.username=
spring.datasource.password=

DB 유지하고싶을때 :
spring.jpa.hibernate.ddl-auto= none || update

create : 스프링부트 시작 시 기존 데이터를 전부 삭제 후 데이터 생성
create-drop : 데이터 생성 후 종료시 데이터 삭제
update : 변경된 데이터를 추가(테이블, 컬럼 등)
none : 스프링부트 시작시 데이터 변경/삭제 하지 않음(단, 테이블,컬럼이 변경되었을 경우 실행 안될 수 있음.)

```

### **application-oauth.properties**

```java
spring.security.oauth2.client.registration.google.client-id=${ClientId}
spring.security.oauth2.client.registration.google.client-secret=${ClientSecret}
```
