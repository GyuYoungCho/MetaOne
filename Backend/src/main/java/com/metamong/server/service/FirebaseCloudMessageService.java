package com.metamong.server.service;

import java.util.List;

import com.metamong.server.dto.UserDto;
import com.metamong.server.dto.UserDto.Response;
import com.metamong.server.entity.FirebaseToken;

public interface FirebaseCloudMessageService {
	
	void save(Response myRes, String token);
	
	void del(String token);
	
	void deleteLastDay();

	List<FirebaseToken> getUsersToken(List<UserDto> users);

	List<FirebaseToken> getBroadcastToken();


}
