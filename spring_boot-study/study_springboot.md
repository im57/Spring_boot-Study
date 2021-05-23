## 스프링부트 동작원리

> #### **(1) 내장 톰켓을 가진다.**
>
> 톰켓을 따로 설치할 필요 없이 바로 실행가능하다.
>
>  
>
> #### **(2) 서블릿 컨테이너**
>
> 
>
> ![img](https://blog.kakaocdn.net/dn/OCwgQ/btqCtYeuOMu/ETtkL2p1e0IewF5IRq6WGK/img.png)
>
> 
>
> #### **(3) web.xml**
>
> - ServletContext의 초기 파라미터
>
> - Session의 유효시간 설정
>
> - Servlet/JSP에 대한 정의
>
> - Servlet/JSP 매핑
>
> - Mime Type 매핑
>
> - Welcome File list
>
> - Error Pages 처리
>
> - 리스너/필터 설정
>
> - 보안
>
> 
> 여기에서 Servlet/JSP 매핑시(web.xml에 직접 매핑 or **@WebServlet 어노테이션 사용)**에 모든 클래스에 매핑을 적용시키기에는 코드가 너무 복잡해지기 때문에 FrontController 패턴을 이용함.
>
>  
>
> 참고 : https://galid1.tistory.com/487
>
> #### **(4) FrontController 패턴**
>
> 최초 앞단에서 request 요청을 받아서 필요한 클래스에 넘겨준다. 왜? web.xml에 다 정의하기가 너무 힘듬.
>
>  
>
> 이때 새로운 요청이 생기기 때문에 request와 response가 새롭게 new될 수 있다. 그래서 아래의 RequestDispatcher가 필요하다.
>
>  
>
> #### **(5) RequestDispatcher**
>
> 필요한 클래스 요청이 도달했을 때 FrontController에 도착한 request와 response를 그대로 유지시켜준다.
>
>  
>
> #### **(6) DispatcherServlet**
>
> FrontController 패턴을 직접짜거나 RequestDispatcher를 직접구현할 필요가 없다. 왜냐하면 스프링에는 DispatcherServlet이 있기 때문이다. DispatcherServlet은 FrontController 패턴 + RequestDispatcher이다.
>
>  
>
> DispatcherServlet이 자동생성되어 질 때 수 많은 객체가 생성(IoC)된다. 보통 필터들이다. 해당 필터들은 내가 직접 등록할 수 도 있고 기본적으로 필요한 필터들은 자동 등록 되어진다.
>
>  
>
> #### **(7) 스프링 컨테이너**
>
> DispatcherServlet에 의해 생성되어지는 수 많은 객체들은 ApplicationContext에서 관리된다. 이것을 IoC라고 한다.
>
>  
>
> **ApplicationContext**
>
> IoC란 제어의 역전을 의미한다. 개발자가 직접 new를 통해 객체를 생성하게 된다면 해당 객체를 가르키는 레퍼런스 변수를 관리하기 어렵다. 그래서 스프링이 직접 해당 객체를 관리한다. 이때 우리는 주소를 몰라도 된다. 왜냐하면 필요할 때 DI하면 되기 때문이다.
>
>  
>
> DI를 의존성 주입이라고 한다. 필요한 곳에서 ApplicationContext에 접근하여 필요한 객체를 가져올 수 있다. ApplicationContext는 싱글톤으로 관리되기 때문에 어디에서 접근하든 동일한 객체라는 것을 보장해준다.
>
>  
>
> ApplicationContext의 종류에는 두가지가 있는데 (root-applicationContext와 servlet-applicationContext) 이다.
>
>  
>
> **a. servlet-applicationContext**
>
> servlet-applicationContext는 ViewResolver, Interceptor, MultipartResolver 객체를 생성하고 웹과 관련된 어노테이션 Controller, RestController를 스캔 한다.
>
> ============> 해당 파일은 DispatcherServlet에 의해 실행된다. 
>
>  
>
> **b. root-applicationContext**
>
> root-applicationContext는 해당 어노테이션을 제외한 어노테이션 Service, Repository등을 스캔하고 DB관련 객체를 생성한다. (스캔이란 : 메모리에 로딩한다는 뜻)
>
> ============> 해당 파일은 ContextLoaderListener에 의해 실행된다. ContextLoaderListener를 실행해주는 녀석은 web.xml이기 때문에 root-applicationContext는 servlet-applicationContext보다 먼저 로드 된다.
>
>  
>
> 당연히 servlet-applicationContext에서는 root-applicationContext가 로드한 객체를 참조할 수 있지만 그 반대는 불가능하다. 생성 시점이 다르기 때문이다.
>
> 
>
> ![img](https://blog.kakaocdn.net/dn/q43e6/btqCvx1OYiy/MJv6bpvTjEtC4XsNsR4m71/img.png)
>
> 
>
>  
>
> **Bean Factory**
>
> 필요한 객체를 Bean Factory에 등록할 수 도 있다. 여기에 등록하면 초기에 메모리에 로드되지 않고 필요할 때 getBean()이라는 메소드를 통하여 호출하여 메모리에 로드할 수 있다. 이것 또한 IoC이다. 그리고 필요할 때 DI하여 사용할 수 있다. ApplicationContext와 다른 점은 Bean Factory에 로드되는 객체들은 미리 로드되지 않고 필요할 때 호출하여 로드하기 때문에 lazy-loading이 된다는 점이다.
>
>  
>
> #### **(8) 요청 주소에 따른 적절한 컨트롤로 요청 (Handler Mapping)**
>
> GET요청 => http://localhost:8080/post/1
>
> 해당 주소 요청이 오면 적절한 컨트롤러의 함수를 찾아서 실행한다.
>
>  
>
> 참고: https://minwan1.github.io/2017/10/08/2017-10-08-Spring-Container,Servlet-Container/
>
> #### **(9) 응답**
>
> html파일을 응답할지 Data를 응답할지 결정해야 하는데 html 파일을 응답하게 되면 ViewResolver가 관여하게 된다.
>
> 하지만 Data를 응답하게 되면 MessageConverter가 작동하게 되는데 메시지를 컨버팅할 때 기본전략은 json이다.
>
>  
>
> 
>
> ![img](https://blog.kakaocdn.net/dn/qntbk/btqCzBhZ33L/ifWzqKL76nFdalVNzKApk1/img.png)
>
> 



---



### HTTP가 무엇인지 궁금해요!



- Socket : 운영체제가 가지고 있는 것

  - 소켓통신 - 계속 연결 -> 부하  up

  ![image-20210523142318928](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523142318928.png)



- http : html문서 전달 목적

  - http 통신 - stateless 방식 (응답 후 연결 끊음)

  ![image-20210523142650319](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523142650319.png)





---



### tomcat이란?



- 웹서버 : static 자원(정적인 자원) 응답

  ![image-20210523144032879](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523144032879.png)



- tomcat 

  - 아파치
    - 요청파일 응답해줌
    - 자바코드 요청시 응답하지 못함 -> 이해하지 못하는 파일의 요청이 오면 제어권을 톰캣에 넘김

  - 톰캣 
    - jsp 파일의 모든 자바코드를 컴파일하고, 컴파일된 데이터를 html 문서로 만들어줌

  ![image-20210523144613183](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523144613183.png)



---



### 서블릿 객체의 생명주기를 알려줘요!



- URL : 자원 접근

  ex) http://naver.com/a.png

- URI : 식별자 접근

  ex) http://naver.com/picture/a



- 서블릿 : 자바코드로 웹 할 수 있는 것

  서블릿 컨테이너 : 서블릿들이 모아놓은 것

  - 서블릿 객체는 일정 개수만 만들고  재사용

  ![img](https://blog.kakaocdn.net/dn/OCwgQ/btqCtYeuOMu/ETtkL2p1e0IewF5IRq6WGK/img.png)

  

  ![image-20210523145602553](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523145602553.png)



---



### web.xml은 무엇인가요?



- ServletContext의 초기 파라미터

- Session의 유효시간 설정

- Servlet/JSP에 대한 정의

- Servlet/JSP 매핑

  : 어디로 가야하는지 알려줌

- Mime Type 매핑

  : 들고 온 데이터 타입 알려줌 / 없다면 get(select)

- Welcome File list

- Error Pages 처리

- 리스너/필터 설정

  - 리스너 - 입구에서 대리인 역할

  - 필터 - (조건에) 적합하지 못하면 걸러줌

- 보안



---



### DispatcherServlet은 무엇인가요?



- FrontController : 특정주소 들어오면 FrontController로 들어옴

  - 새로운 요청이 생기기 때문에 request와 response가 새롭게 new 생성 -> request와 response 유지 필요

  ![image-20210523151636260](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523151636260.png)



- RequestDispatcher - request와 response를 그대로 유지시켜줌 (데이터들고 페이지 이동 가능해짐

  ![image-20210523152144993](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523152144993.png)



- DispatcherServlet : FrontController 패턴 + RequestDispatcher
  - 스프링에는 DispatcherServlet이 있기 때문에 FrontController 패턴을 직접짜거나 RequestDispatcher를 직접구현할 필요 없음



---



### ApplicationContext가 무엇인가요?



- static - main 메소드 실행 전부터 존재
- 자바파일 - 객체로 메모리에 띄움 (생성, 소멸)



- DispatcherServlet - 컴포넌트 스캔하여 (어노테이션 확인) 주소 분배



- ApplicationContext

  - root-applicationContext 

    -> ContextLoaderListener 가 읽어서 공통적으로 사용하는 객체(db 등) 메모리에 띄움 

    - root-applicationContext는 servlet-applicationContext보다 먼저 로드

  - servlet-applicationContext

    -> DispatcherServlet 가 읽어서 웹 관련 객체만 메모리에 띄움

    - root-applicationContext가 로드한 객체를 참조 가능



- Bean Factory

  : 미리 로드되지 않고 필요할 때 호출하여 로드하기 때문에 lazy-loading 가능

  ```java
  @Configuration
  Class A{
  	@Bean //필요시 로드
  	객체 메소드(){
  		return 객체;
  	}
  }
  ```



---



### 응답(Response)하는 방법



- ViewResolver : html 파일 응답
  - 자동으로 prefix 값 붙여줌
- MessageConverter : Data 응답
  - JSON으로 응답



![image-20210523155505925](C:\Users\USER\AppData\Roaming\Typora\typora-user-images\image-20210523155505925.png)