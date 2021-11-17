package com.metamong.server.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.metamong.server.dto.MessageDto;
import com.metamong.server.dto.MessageDto.MyMessageResponse;
import com.metamong.server.dto.MessageDto.OneMessageResponse;
import com.metamong.server.dto.OnlineDto;
import com.metamong.server.entity.FirebaseToken;
import com.metamong.server.entity.Message;
import com.metamong.server.entity.User;
import com.metamong.server.repository.FirebaseTokenRepository;
import com.metamong.server.repository.MessageRepository;
import com.metamong.server.repository.UserRepository;
import com.metamong.server.service.FirebaseCloudMessageService;
import com.metamong.server.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("*")
public class MessageController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FirebaseTokenRepository firebaseTokenRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	
	@Autowired
    private MessageService messageService;
	@Autowired
    private FirebaseCloudMessageService firebaseCloudMessageService;
	/***
	 	 * @param : 개인 쪽지 WhoseNote
		     * nickname : 닉네임 String
		     * title : 제목 String
			 * content : 내용 String
		     * firebaseToken : 파이어베이스 토큰 String
	     * @return : Created(201)
	     * @throws IOException : 자동 완성
	 * @throws InterruptedException 
	 * @throws FirebaseMessagingException 
	*/
	
	@PostMapping("/private")
	@ApiOperation(value = "개인 쪽지를 발신한다.")
	public ResponseEntity<String> sendOne(@RequestBody @Valid MessageDto.MRegisterRequest messageForm, HttpServletRequest request) throws IOException, FirebaseMessagingException, InterruptedException {

		System.out.println(messageForm.getNickname());

		Optional<User> recv_user = userRepository.findByNickname(messageForm.getNickname());
		int userId = (int) request.getAttribute("userId");

		Optional<User> send_user = userRepository.findById(userId);
		System.out.println(recv_user.get().getEmail());
		
		if(!send_user.isPresent() || !recv_user.isPresent())
			return ResponseEntity.noContent().build();
		System.out.println("hi");
		
		Message regMessage = messageService.registerMessage(messageForm,send_user.get(),recv_user.get());
		List<FirebaseToken> recv_token = new ArrayList<>();
		Optional<List<FirebaseToken>> user_token = firebaseTokenRepository.findByUserId(recv_user.get().getId());
		recv_token.add(user_token.get().get(0));
		firebaseCloudMessageService.sends(recv_token, send_user.get().getNickname(), regMessage.getTitle(), regMessage.getContent());
		
		return ResponseEntity.status(201).build();
	}
	
	@GetMapping("/private")
	@ApiOperation(value = "개인 쪽지리스트 조회")
	public ResponseEntity<List<MyMessageResponse>> selectMy(HttpServletRequest request) throws IOException { 
		int userId = (int) request.getAttribute("userId");
		
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
		int userId = (int) request.getAttribute("userId");
		
		Optional<User> my = userRepository.findById(userId);
		
		if(!your.isPresent() || !my.isPresent())
			return ResponseEntity.noContent().build();
		
		List<OneMessageResponse> oneMessageList = messageService.selectOneMessage(my.get(), your.get());
		
		if(oneMessageList==null) return new ResponseEntity<>(oneMessageList,HttpStatus.NO_CONTENT);
			
		return new ResponseEntity<>(oneMessageList,HttpStatus.OK);
	}
	
	@PutMapping("/private")
	@ApiOperation(value = "메세지 읽기")
	public ResponseEntity<String> readMessage(@RequestParam String msgId, HttpServletRequest request) throws IOException {
		Optional<Message> message = messageRepository.getById(Integer.parseInt(msgId));
		
		if(!message.isPresent())
			return ResponseEntity.noContent().build();
		
		Message m = message.get();
		m.setIsRead(1);
		messageRepository.save(m);
		
		return ResponseEntity.status(200).build();
	}

	/***
	 * @return : List<Online> 
		* isOnline: boolean, nickname: String, email: String,
	 * @throws IOException : 자동 완성
	*/
	
	@GetMapping("/online")
	@ApiOperation(value = "사용자 전체 Online 정보를 확인한다.")
	public ResponseEntity<List<OnlineDto>> userOnline( HttpServletRequest request) throws IOException {
		int userId = (int) request.getAttribute("userId");
		
		Optional<List<User>> userlist = userRepository.findByIdNot(userId);
		
		if(!userlist.isPresent())
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		List<OnlineDto> onlinelist = new ArrayList<>();
		for(User u : userlist.get()) {
			OnlineDto od = OnlineDto.builder().isOnline(u.getState()==1?true:false)
					.nickname(u.getNickname())
					.email(u.getEmail())
					.characid(u.getCharacter()==null?0:u.getCharacter().getId()).
					build();
			onlinelist.add(od);
		}
		if(onlinelist.isEmpty())
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(onlinelist,HttpStatus.OK);
	}
}
