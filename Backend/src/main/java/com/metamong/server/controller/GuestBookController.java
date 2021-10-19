package com.metamong.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/guestbook")
@CrossOrigin("*")
public class GuestBookController {
	
	/***
	     * @param Content : 작성한 내용
	     	* content : String 
	     * @return : Created(201)
	     * @throws IOException : 자동 완성
	*/
	
	@PostMapping("")
	@ApiOperation(value = "방명록을 작성한다.")
	public ResponseEntity<String> write(@RequestBody Object content) throws IOException { 
	  return ResponseEntity.status(201).build();
	}
	
	/***
	     * @param Content : 작성한 내용
	     	* content : String 
	     * @return : OK(200)
	     * @throws IOException : 자동 완성
	*/
	
	@PutMapping("")
	@ApiOperation(value = "방명록을 수정한다.")
	public ResponseEntity<String> modify(@RequestBody Object content) throws IOException { 
	  return ResponseEntity.ok().build();
	}
	
	/***
	     * @param date : 날짜, 안되면 String으로 바꾸기
	     * @return : List<TodayGuestBook>
	     	* nickname: String, content: String, createAt: Datetime
	     * @throws IOException : 자동 완성
	*/
	
	@GetMapping("")
	@ApiOperation(value = "방명록을 조회한다.")
	public ResponseEntity<List<Object>> select(@RequestParam Date date) throws IOException { 
	  return ResponseEntity.ok().build();
	}
}
