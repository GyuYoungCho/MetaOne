package com.metamong.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.metamong.server.dto.MessageDto;
import com.metamong.server.dto.MessageDto.MyMessageResponse;
import com.metamong.server.dto.MessageDto.OneMessageResponse;
import com.metamong.server.dto.OnlineDto;
import com.metamong.server.entity.User;
import com.metamong.server.repository.FirebaseTokenRepository;
import com.metamong.server.repository.MessageRepository;
import com.metamong.server.repository.UserRepository;
import com.metamong.server.service.FirebaseInitService;
import com.metamong.server.service.MessageService;


import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("*")
public class MessageController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FirebaseTokenRepository firebaseTokenRepository;
	
	
	@Autowired
    private MessageService messageService;
	@Autowired
	private MessageRepository messageRepository;
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
	public ResponseEntity<String> sendOne(@RequestBody @Valid MessageDto.MRegisterRequest messageForm, HttpServletRequest request) throws IOException {
		
		Optional<User> recv_user = userRepository.findByNickname(messageForm.getNickname());
		// int userId = (int) request.getAttribute("userId");
		int userId = 1;
		Optional<User> send_user = userRepository.findById(userId);
		System.out.println(recv_user.get().getEmail());
		if(!send_user.isPresent() || !recv_user.isPresent())
			return ResponseEntity.noContent().build();
		System.out.println("hi");
		messageService.registerMessage(messageForm,recv_user.get(),send_user.get());
		return ResponseEntity.status(201).build();
	}
	
	@GetMapping("/private")
	@ApiOperation(value = "개인 쪽지리스트 조회")
	public ResponseEntity<List<MyMessageResponse>> selectMy(HttpServletRequest request) throws IOException { 
		// int userId = (int) request.getAttribute("userId");
		int userId = 1;
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		List<MyMessageResponse> myMessageList = messageService.selectMyMessage(user.get());
		if(myMessageList==null) return new ResponseEntity<>(myMessageList,HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(myMessageList, HttpStatus.OK);
	}

	/***
		 * @param date : 날짜, 안되면 String으로 바꾸기
		 * @return : MyNote
		 	* whose: boolean(내 메시지, 상대 메시지), nickname : String (보낸 사람), 
		 	* title: String, content: String, createAt: Datetime,
		 * @throws IOException : 자동 완성
	*/
	
	@GetMapping("/private/{nickname}")
	@ApiOperation(value = "개인 1:1 쪽지 조회")
	public ResponseEntity<List<OneMessageResponse>> selectOneByOne(@PathVariable String nickname, HttpServletRequest request) throws IOException { 
		Optional<User> your = userRepository.findByNickname(nickname);
		// int userId = (int) request.getAttribute("userId");
		int userId = 1;
		Optional<User> my = userRepository.findById(userId);
		
		if(!your.isPresent() || !my.isPresent())
			return ResponseEntity.noContent().build();
		
		List<OneMessageResponse> oneMessageList = messageService.selectOneMessage(my.get(), your.get());
		
		if(oneMessageList==null) return new ResponseEntity<>(oneMessageList,HttpStatus.NO_CONTENT);
			
		return new ResponseEntity<>(oneMessageList,HttpStatus.OK);
	}

	/***
	 	 * @param : Note
	 	 * title : 제목 String, content : 내용 String
	     * @return : Created(201)
		 * @throws IOException : 자동 완성
	*/
	
	@PostMapping("/public")
	@ApiOperation(value = "전체 쪽지를 발신한다")
	public ResponseEntity<String> sendAll(@RequestBody MessageDto.AllSendRequest allSend, HttpServletRequest request) throws IOException { 
		return ResponseEntity.status(201).build();
	}

	/***
	 * @return : List<Online> 
		* isOnline: boolean, nickname: String, email: String,
	 * @throws IOException : 자동 완성
	*/
	
	@GetMapping("/online")
	@ApiOperation(value = "사용자 전체 Online 정보를 확인한다.")
	public ResponseEntity<List<OnlineDto>> userOnline() throws IOException {
		List<User> userlist = userRepository.findAll();
		List<OnlineDto> onlinelist = new ArrayList<>();
		for(User u : userlist) {
			OnlineDto od = OnlineDto.builder().isOnline(u.getState()==1?true:false)
					.nickname(u.getNickname())
					.email(u.getEmail()).
					build();
			onlinelist.add(od);
		}
		if(onlinelist.isEmpty())
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(onlinelist,HttpStatus.OK);
	}
}
