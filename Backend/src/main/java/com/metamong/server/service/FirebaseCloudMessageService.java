package com.metamong.server.service;

import java.io.IOException;
import java.util.List;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.FirebaseToken;

public interface FirebaseCloudMessageService {
	
	void save(UserDto.Response myRes, String token);
	
	void del(String token);
	
	void deleteLastDay();

	List<FirebaseToken> getUserToken(int userId);

	List<FirebaseToken> getBroadcastToken();

	void sends(List<FirebaseToken> tokens, String messageKey, String title, String body)
			throws InterruptedException, IOException, FirebaseMessagingException;


}
