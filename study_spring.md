### 스프링의 핵심은 무엇인가요?



- 프레임워크 

  : 틀안에서 동작

  

- 오픈소스 

  : 소스코드 공개 -> 내부를 뜯어 고칠 수 있음

  

- IoC 컨테이너 (Inversion of controll - 제어의 역전)

  : 객체를 생성할 때 new로 생성하지 않아도, 스프링이 객체들을 생성 + 관리

  

- DI (Dependency Injection - 의존성 주입) 지원

  : 스프링 컨테이너에 등록된 객체들을 모든 클래스에서 사용 가능



---



### 필터란 무엇인가요?



- 필터 - 권한 체크하여 검열 기능 (= 문지기)
  - 스프링 자체가 기본적으로 제공하는 필터 사용
  - 직접 필터 생성하여 사용



- 톰캣의 필터 - web.xml
- 스프링 컨테이너의 필터 - 인터셉터



- 컴파일 체킹

  - @어노테이션 : 주석 + 힌트 (컴파일러가 무시하지 않음)

    스프링에서는 어노테이션으로 객체 생성

    ```java
    @Component // 클래스를 메모리에 로딩 -> IoC
    @Autowired // 로딩된 객체를 해당 변수에 연결 -> DI
    ```

    cf) // : 주석 (컴파일러가 무시)





---



### MessageConverter가 궁금해요



- 중간언어

  - JSON, XML

    ex) MessageConverter : Jackson - Json 데이터로 바꿔줌

    ​		자바 객체 -> JSON -> 파이썬 객체

    ​		자바 객체 <- JSON <- 파이썬 객체



- BufferedReader, BufferedWriter

  - 영어 한 문자 - 8bit 필요 (256가지 문자 전송) = 1바이트

  - 가변길이의 문자를 주고 받을 수 있음

    ```java
    @Responsebody // BufferedWriter 역할
    @RequestBody  // BufferedReader 역할
    ```