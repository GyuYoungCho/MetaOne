package com.metamong.server.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.*;
import com.metamong.server.dto.FcmMessage;
import com.metamong.server.dto.UserDto;
import com.metamong.server.dto.UserDto.Response;
import com.metamong.server.entity.FirebaseToken;
import com.metamong.server.entity.User;
import com.metamong.server.repository.FirebaseTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//import okhttp3;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageServiceImpl implements FirebaseCloudMessageService{
    
    @Autowired
	private FirebaseTokenRepository firebaseTokenRepository;
    
    @Override
    public void sends(List<FirebaseToken> tokens, String nickname, String title, String body) throws InterruptedException, IOException, FirebaseMessagingException {
        if(tokens.size() == 0) return;
        
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(null)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build())
                        .data(FcmMessage.Data.builder()
                            .nickname(nickname)
                            .build())
                        .build()
                )
                .validate_only(false)
                .build();
        
        MulticastMessage multicastMessage = makeMulticastMessage(tokens,fcmMessage);

        BatchResponse response = FirebaseMessaging.getInstance()
                .sendMulticast(multicastMessage);


        if (response.getFailureCount() > 0) {
            List<SendResponse> responses = response.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    System.out.println(responses.get(i).getException());
                    failedTokens.add(tokens.get(i).getToken());
                }
            }
        }
    }
    
    private MulticastMessage makeMulticastMessage(List<FirebaseToken> tokens, FcmMessage fcm){
        List<String> tokenList = new ArrayList<>();
        for(FirebaseToken token : tokens) tokenList.add(token.getToken());
        
        return MulticastMessage.builder()
                .setNotification(new Notification(fcm.getMessage().getNotification().getTitle(), fcm.getMessage().getNotification().getBody()))
                .putData("nickname", String.valueOf(fcm.getMessage().getData().getNickname() ))
                .addAllTokens(tokenList)
                .build();
    }

    /**
     * 로그인 시 DB에 Firebase 연동 토큰 저장
     * @param myRes : 유저 정보
     * @param token : Firebase 연동 토큰
     */
    @Override
    @Transactional
    public void save(UserDto.LoginRes loginRes, String token) {
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByUserIdAndToken(loginRes.getId(), token);
        if(firebaseTokens.isPresent() && firebaseTokens.get().size() >= 1) return;

        User user = new User();
        user.setId(loginRes.getId());
        FirebaseToken firebaseToken = FirebaseToken.builder()
                .user(user)
                .token(token)
                .createAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("+9")).toInstant()))
                .build();

        firebaseTokenRepository.save(firebaseToken);
    }
    
    
    @Override
    @Transactional
	public void save(Response loginRes, String token) {
    	Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByUserIdAndToken(loginRes.getId(), token);
        if(firebaseTokens.isPresent() && firebaseTokens.get().size() >= 1) return;

        User user = new User();
        user.setId(loginRes.getId());
        FirebaseToken firebaseToken = FirebaseToken.builder()
                .user(user)
                .token(token)
                .createAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("+9")).toInstant()))
                .build();

        firebaseTokenRepository.save(firebaseToken);
		
	}

    
    /**
     * 특정 유저의 Firebase Auth Token 들 가져오기 -> 사용자가 여러 브라우저로 접속했을 때 모두 캐스팅을 위해
     * @param userId : 유저 ID
     * @return : Firebase 인증 토큰
     */
    @Override
    public List<FirebaseToken> getUserToken(int userId) {
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByUserId(userId);

        return firebaseTokens.orElse(null);
    }

    /**
     * 현재 접속해 있는 모든 사용자에게 캐스팅을 위해
     * @return :  Firebase 인증 토큰
     */
    @Override
    public List<FirebaseToken> getBroadcastToken() {
        List<FirebaseToken> firebaseTokens = firebaseTokenRepository.findAll();

        if(firebaseTokens.size() == 0) return null;
        return firebaseTokens;
    }

    /**
     * 로그아웃 시 해당 브라우저 연동 Firebase 토큰 DB에서 삭제
     * @param token : 토큰
     */
    @Override
    @Transactional
    public void del(String token) {
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByToken(token);

        firebaseTokens.ifPresent(select -> {
            firebaseTokenRepository.deleteAll(select);
        });
    }

    /**
     * 하루 전까지의 Trash Firebase Token 정보 삭제하기 (알림 관련)
     */
    @Override
    @Transactional
    public void deleteLastDay() {
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByCreateAtBefore(LocalDateTime.now(ZoneId.of("+9")).minusDays(1));

        firebaseTokens.ifPresent(select ->{
            firebaseTokenRepository.deleteAll(firebaseTokens.get());
        });
    }

    



    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/greenfingers-3cbec-firebase-adminsdk-ckq86-7871b76c97.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }
    
}