package com.metamong.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("*")
public class MessageController {
	/***
	 	 * @param : 개인 쪽지 WhoseNote
		     * nickname : 닉네임 String
		     * title : 제목 String
			 * content : 내용 String
		     * firebaseToken : 파이어베이스 토큰 String
	     * @return : Created(201)
	     * @throws IOException : 자동 완성
	*/
	
	@PostMapping("/private")
	@ApiOperation(value = "개인 쪽지를 발신한다.")
	public ResponseEntity<String> sendOne(@RequestBody Object mynote) throws IOException { 
		return ResponseEntity.status(201).build();
	}

	/***
		 * @param date : 날짜, 안되면 String으로 바꾸기
		 * @return : MyNote
		 	* whose: boolean(내 메시지, 상대 메시지), nickname : String (보낸 사람), 
		 	* title: String, content: String, createAt: Datetime,
		 * @throws IOException : 자동 완성
	*/
	
	@GetMapping("/private/{nickname}")
	@ApiOperation(value = "개인 쪽지를 조회한다.")
	public ResponseEntity<Object> select(@PathVariable String nickname) throws IOException { 
		return ResponseEntity.ok().build();
	}

	/***
	 	 * @param : Note
	 	 * title : 제목 String, content : 내용 String
	     * @return : Created(201)
		 * @throws IOException : 자동 완성
	*/
	
	@PostMapping("/public")
	@ApiOperation(value = "전체 쪽지를 발신한다")
	public ResponseEntity<String> sendAll(@RequestBody Object note) throws IOException { 
		return ResponseEntity.status(201).build();
	}

	/***
	 * @return : List<Online> 
		* isOnline: boolean, nickname: String, email: String,
	 * @throws IOException : 자동 완성
	*/
	
	@GetMapping("/online")
	@ApiOperation(value = "사용자 전체 Online 정보를 확인한다.")
	public ResponseEntity<List<Object>> userOnline() throws IOException { 
		return ResponseEntity.ok().build();
	}
}
