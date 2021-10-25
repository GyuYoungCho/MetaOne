package com.metamong.server.service;

import com.metamong.server.dto.EmailDto;
import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {

    @Async
    public void sendEmail(EmailDto emailDto);

    public String createKey();
}
