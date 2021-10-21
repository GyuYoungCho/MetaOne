package com.metamong.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metamong.server.dto.MessageDto;
import com.metamong.server.dto.MessageDto.MyMessageResponse;
import com.metamong.server.dto.MessageDto.OneMessageResponse;
import com.metamong.server.dto.MessageDto.ResponseList;
import com.metamong.server.entity.Message;
import com.metamong.server.entity.User;
import com.metamong.server.repository.FirebaseTokenRepository;
import com.metamong.server.repository.MessageRepository;
import com.metamong.server.repository.UserRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private FirebaseTokenRepository firebaseTokenRepository;
	
	@Override
	public void registerMessage(MessageDto.RegisterRequest messageForm, User sent_user, User recv_user) {
		
		Message message = new Message(sent_user,recv_user,messageForm.getTitle(),
					messageForm.getContent(),new Date(),0);
		messageRepository.save(message);
	}

	@Override
	public List<MyMessageResponse> selectMyMessage(User user) {
		Optional<List<Message>> mymessage = messageRepository.findByRecvUserId(user);
		List<MyMessageResponse> myMessageList = new ArrayList<>();
		
		if (!mymessage.isPresent()) return null;
		for(Message m : mymessage.get()) {
			String nickname = m.getSentUserId().getNickname();
			MyMessageResponse mmr = MyMessageResponse.builder()
					.isRead(m.getIsRead()==1?true:false)
					.nickname(nickname)
					.createAt(new Date())
					.build();
			
			myMessageList.add(mmr);
		}
		Collections.sort(myMessageList, (o1,o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));
		return myMessageList;
	}

	@Override
	public List<OneMessageResponse> selectOneMessage(User my, User your) {
		Optional<List<Message>> sentList = messageRepository.findBySentUserIdAndRecvUserId(my,your);
		Optional<List<Message>> recvList = messageRepository.findBySentUserIdAndRecvUserId(your,my);
		
		List<OneMessageResponse> myOneMessageList = new ArrayList<>();
		
		if (!sentList.isPresent() && !recvList.isPresent()) return null;
		
		String nickname = my.getNickname();
		for(Message m : sentList.get()) {
			
			OneMessageResponse omr = OneMessageResponse.builder()
					.whose(true)
					.nickname(nickname)
					.title(m.getTitle())
					.content(m.getContent())
					.createAt(new Date())
					.build();
			
			myOneMessageList.add(omr);
		}
		
		nickname = your.getNickname();
		for(Message m : recvList.get()) {
			
			OneMessageResponse omr = OneMessageResponse.builder()
					.whose(false)
					.nickname(nickname)
					.title(m.getTitle())
					.content(m.getContent())
					.createAt(new Date())
					.build();
			
			myOneMessageList.add(omr);
		}
		
		Collections.sort(myOneMessageList, (o1,o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));
		return myOneMessageList;
		
	}

}
