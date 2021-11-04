package com.metamong.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.metamong.server.dto.GuestBookDto;
import com.metamong.server.service.GuestBookService;
import com.metamong.server.service.GuestBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/guestbook")
public class GuestBookController {

	private final GuestBookService guestBookService;

	@Autowired
	public GuestBookController(GuestBookServiceImpl guestBookServiceImpl) {
		this.guestBookService = guestBookServiceImpl;
	}

	/**
	 *
	 * @param guestBook : 내용
	 * @param request : Client 정보
	 * @return
	 * @throws IOException
	 */
	@PostMapping("")
	@ApiOperation(value = "방명록을 작성한다.")
	public ResponseEntity<String> write(@RequestBody GuestBookDto.GuestBookReq guestbook, HttpServletRequest request) throws IOException {
		int userId = (int) request.getAttribute("userId");
		

		guestBookService.registerGuestBook(guestbook.getContent(), userId);

	  	return ResponseEntity.status(201).build();
	}

	/**
	 *
	 * @param guestBook : 내용, 수정할 GuestBook ID
	 * @param request : Client 정보
	 * @return
	 * @throws IOException
	 */
	@PutMapping("")
	@ApiOperation(value = "방명록을 수정한다.")
	public ResponseEntity<String> modify(@RequestBody GuestBookDto guestBook, HttpServletRequest request) throws IOException {
		int userId = (int) request.getAttribute("userId");

		guestBookService.updateGuestBook(guestBook, userId);

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ResponseEntity select(@RequestParam String date) throws IOException {
		GuestBookDto.ResponseList guestBookDtoList = guestBookService.getGuestBook(date);
		System.out.println(guestBookDtoList.getData());

	  	return ResponseEntity.ok().body(guestBookDtoList);
	}
}
