package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자 요청 -> 응답(HTML파일)
//@Controller

//사용자 요청 -> 응답(data)
@RestController
public class HttpTestController {

	@GetMapping("http/lombok")
	public String lomokTest() {
		//Member m = new Member(1,"tom","1234","abc@gmail.com");
		Member m = Member.builder().username("tom").password("1234").email("abc@gmail.com").build();
		System.out.println(m.getId());
		m.setId(2);
		System.out.println(m.getId());
		return "lombok success";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청만 가능
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(
			/*@RequestParam int id,
			@RequestParam String username*/
			Member m) {
		return "get: " + m.getId() +", " + m.getUsername()+", " + m.getPassword()+", " + m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(
			@RequestBody Member m	//body : raw -> application/json (mime type)
			//@RequestBody String text 	//body : raw -> text/plain (mime type)
			){
		return "post" + m.getId() +", " + m.getUsername()+", " + m.getPassword()+", " + m.getEmail();
		//return "post: " + text;
	}
	
	//http://localhost:8080/http/putdelete (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put" + m.getId() +", " + m.getUsername()+", " + m.getPassword()+", " + m.getEmail();
	}
	
	//http://localhost:8080/http/get (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete";
	}
}
