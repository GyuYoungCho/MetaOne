package com.metamong.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineDto {
	private boolean isOnline;
	private String nickname;
	private String email;
	private int characid;
}
