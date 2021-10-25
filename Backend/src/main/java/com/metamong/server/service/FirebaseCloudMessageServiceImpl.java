package com.metamong.server.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.metamong.server.dto.FcmMessage;
import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.FirebaseToken;
import com.metamong.server.entity.User;
import com.metamong.server.repository.FirebaseTokenRepository;

import okhttp3.*;
//import okhttp3;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageServiceImpl implements FirebaseCloudMessageService{

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/greenfingers-3cbec/messages:send";
    private final ObjectMapper objectMapper;
    
    @Autowired
	private FirebaseTokenRepository firebaseTokenRepository;

    public void sendMessageTo(String targetToken, String messageKey, String title, String body) throws IOException {
        String message = makeMessage(targetToken, messageKey, title, body);

        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody requestBody
                = okhttp3.RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json;")
                .build();
        Response response = client.newCall(request)
                .execute();

        System.out.println(response.body().string());

    }

    private String makeMessage(String targetToken, String messageKey, String title, String body)
            throws JsonProcessingException {

        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build())
                        .data(FcmMessage.Data.builder()
                            .msgId(messageKey)
                            .build())
                        .build()
                )
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    /**
     * 로그인 시 DB에 Firebase 연동 토큰 저장
     * @param myRes : 유저 정보
     * @param token : Firebase 연동 토큰
     */
    @Override
    public void save(UserDto.Response myRes, String token) {
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByUserIdAndToken(myRes.getId(), token);
        if(firebaseTokens.isPresent() && firebaseTokens.get().size() >= 1) return;

        User user = new User();
        user.setId(myRes.getId());
        FirebaseToken firebaseToken = FirebaseToken.builder()
                .user(user)
                .token(token)
                .createAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("+9")).toInstant()))
                .build();

        firebaseTokenRepository.save(firebaseToken);
    }

    
    /**
     * 여러 유저에게 캐스팅을 위해 Firebase Auth Token 들 가져오기 (최근 접속한 브라우저 순으로 정렬하여 반환)
     * @param users  : UserDto 리스트
     * @return : Firebase 인증 토큰
     */
    @Override
    public List<FirebaseToken> getUsersToken(List<UserDto> users) {
        List<Integer> userIds = new ArrayList<>();
        for(UserDto user : users) userIds.add(user.getId());
        Optional<List<FirebaseToken>> firebaseTokens = firebaseTokenRepository.findByUserIdInOrderByCreateAtDesc(userIds);

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