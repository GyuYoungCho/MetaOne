package com.metamong.server.service;

import com.metamong.server.config.RedisUtil;
import com.metamong.server.dto.EmailDto;
import com.metamong.server.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

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
    public void sendEmailFindPw(EmailDto emailDto) {
        try {
            System.out.println("----이메일 보내기 시도-----");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
            message.setTo(emailDto.getToEmail());
            message.setFrom(EmailSenderServiceImpl.FROM_ADDRESS);
            message.setSubject(emailDto.getTitle());
//            message.setText(emailDto.getCode());
            
            StringBuffer htmlContent = new StringBuffer();
			htmlContent.append("<!DOCTYPE html>");
			htmlContent.append("<html>");
			htmlContent.append("<head>");
			htmlContent.append("</head>");
			htmlContent.append("<body>");
			htmlContent.append(
					" <div" 																																																	+ 
					"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 600px; border-top: 4px solid #7781d5; border-bottom: 4px solid #7781d5; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
					"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 25px; font-weight: 400;\">"																															+ 
					"		<span style=\"color: #7781d5\">위기탈출 메타원 비밀번호 인증번호</span> 안내입니다."																																				+ 
					"	</h1>\n"																																																+ 
					"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
					emailDto.getToEmail()																																																+
					"		님 안녕하세요.<br />"																																													+ 
					"		아래 인증번호를 확인하고 비밀번호 변경을 완료해주세요.<br />"																													+ 
					"		감사합니다."																																															+ 
					"	</p>"	+
					"	<h2><span style=\"color: #7781d5\">인증번호</span>  "
					+"<strong>"+emailDto.getCode()+"</strong>"+
					"	</h2>"+																																	
					" </div>"	
			);
			htmlContent.append("</body>");
			htmlContent.append("</html>");
            message.setText(htmlContent.toString(), true);
            

            javaMailSender.send(mimeMessage);
            System.out.println("----이메일 보냈다-----");
        } catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException(HttpStatus.valueOf(401), "메일을 보낼 수 없습니다.");
        }
    }

    @Async
    public void sendEmail(EmailDto emailDto) {
        try {
            System.out.println("----이메일 보내기 시도-----");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
            message.setTo(emailDto.getToEmail());
            message.setFrom(EmailSenderServiceImpl.FROM_ADDRESS);
            message.setSubject(emailDto.getTitle());
            
            StringBuffer htmlContent = new StringBuffer();
			htmlContent.append("<!DOCTYPE html>");
			htmlContent.append("<html>");
			htmlContent.append("<head>");
			htmlContent.append("</head>");
			htmlContent.append("<body>");
			htmlContent.append(
					" <div" 																																																	+ 
					"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 600px; border-top: 4px solid #7781d5; border-bottom: 4px solid #7781d5; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
					"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 25px; font-weight: 400;\">"																															+ 
					"		<span style=\"color: #7781d5\">위기탈출 메타원 회원가입 인증번호</span> 안내입니다."																																				+ 
					"	</h1>\n"																																																+ 
					"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
					emailDto.getToEmail()																																																+
					"		님 안녕하세요.<br />"																																													+ 
					"		<span style=\"color: #7781d5\">위기탈출 메타원</span>에 오신걸 진심으로 환영합니다.<br />"																																						+ 
					"		아래 인증번호를 확인하고 회원가입을 완료해주세요.<br />"																													+ 
					"		감사합니다."																																															+ 
					"	</p>"	+
					"	<h2><span style=\"color: #7781d5\">인증번호</span>  "
					+"<strong>"+emailDto.getCode()+"</strong>"+
					"	</h2>"+																																	
					" </div>"	
			);
			htmlContent.append("</body>");
			htmlContent.append("</html>");
            message.setText(htmlContent.toString(), true);
//            message.setText(emailDto.getCode());

            javaMailSender.send(mimeMessage);
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
