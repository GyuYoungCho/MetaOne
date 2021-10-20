package com.metamong.server.service;

import com.metamong.server.dto.GuestBookDto;

public interface GuestBookService {
    void registerGuestBook(String content, int userId);
    void updateGuestBook(GuestBookDto guestBook, int userId);
    GuestBookDto.ResponseList getGuestBook(String date);
}
