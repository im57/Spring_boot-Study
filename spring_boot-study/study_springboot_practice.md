### 의존성 설정

- Spring Boot DevTools

  : 파일 변경될 때, 자동 재시작

- Lombok

  : 생성자, getter/setter 자동 생성

- Spring Data JPA

  : database

- MySQL Driver

- Spring Security

  : 보안 기능 제공

- Spring Web

  : 어노테이션 사용

  : 톰캣 기본 탑재



```
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
</dependency>

<!-- JSTL -->
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>jstl</artifactId>
</dependency>
```