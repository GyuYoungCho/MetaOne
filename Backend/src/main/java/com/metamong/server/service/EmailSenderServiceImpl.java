package com.metamong.server.service;

import com.metamong.server.config.RedisUtil;
import com.metamong.server.dto.EmailDto;
import com.metamong.server.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;
    private final EmailDto emailDto;

    private static final String FROM_ADDRESS = "metamong305@gmail.com";

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender, RedisUtil redisUtil, EmailDto emailDto) {
        this.javaMailSender = javaMailSender;
        this.redisUtil = redisUtil;
        this.emailDto = emailDto;
    }

    @Async
    public void sendEmail(EmailDto emailDto) {
        try {
            System.out.println("----이메일 보내기 시도-----");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDto.getToEmail());
            message.setFrom(EmailSenderServiceImpl.FROM_ADDRESS);
            message.setSubject(emailDto.getTitle());
            message.setText(emailDto.getCode());

            javaMailSender.send(message);
            System.out.println("----이메일 보냈다-----");
            redisUtil.setDataExpire(emailDto.getCode(), emailDto.getToEmail(), emailDto.getValidTime());
            System.out.println("----redis 저장-----");

        } catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException(HttpStatus.valueOf(401), "메일을 보낼 수 없습니다.");
        }
    }
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
