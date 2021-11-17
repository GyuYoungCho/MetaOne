package com.metamong.server.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.FirebaseToken;

import java.io.IOException;
import java.util.List;

public interface FirebaseCloudMessageService {
	
	void save(UserDto.LoginRes loginRes, String token);
	
	void save(UserDto.Response loginRes, String token);
	
	void del(String token);
	
	void deleteLastDay();

	List<FirebaseToken> getUserToken(int userId);

	void sends(List<FirebaseToken> tokens, String messageKey, String title, String body)
			throws InterruptedException, IOException, FirebaseMessagingException;


}
