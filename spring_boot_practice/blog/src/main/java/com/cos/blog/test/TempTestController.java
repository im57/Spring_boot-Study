package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//사용자 요청 -> 응답(data)
//@RestController

//사용자 요청 -> 응답(HTML파일)
@Controller
public class TempTestController {

	//http://localhost:7000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		//파일 리턴 기본 경로 : src/main/resources/static
		//리턴명: /home.html
		//풀경로: src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	//jsp는 정적파일 아님-컴파일 필요. webapp/WEB-INF/views 하위 파일
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/test.jsp
		return "test";
	}
}
