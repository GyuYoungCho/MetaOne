package com.metamong.server.service;

import java.util.List;

import com.metamong.server.dto.MessageDto;
import com.metamong.server.entity.User;

public interface MessageService {
	void registerMessage(MessageDto.MRegisterRequest messageForm,User sent_user, User recv_users);
	List<MessageDto.MyMessageResponse> selectMyMessage(User user);
	List<MessageDto.OneMessageResponse> selectOneMessage(User my, User your);
}
