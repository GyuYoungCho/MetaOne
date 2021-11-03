package com.metamong.server.service;

import com.metamong.server.dto.GuestBookDto;
import com.metamong.server.entity.GuestBook;
import com.metamong.server.entity.User;
import com.metamong.server.repository.GuestBookRepository;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GuestBookServiceImpl implements GuestBookService{
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    @Autowired
    public GuestBookServiceImpl(GuestBookRepository guestBookRepository, UserRepository userRepository){
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void registerGuestBook(String content, int userId) {
        User user = new User();
        user.setId(userId);

        GuestBook guestBook = GuestBook.builder()
                .content(content)
                .createAt(new Date())
                .user(user)
                .build();

        guestBookRepository.save(guestBook);

    }

    @Override
    public void updateGuestBook(GuestBookDto guestBook, int userId) {
        Optional<GuestBook> guestBookOptional = guestBookRepository.findById(guestBook.getId());

        guestBookOptional.ifPresent(guestBookSelect -> {
            guestBookSelect.setContent(guestBook.getContent());
            guestBookSelect.setCreateAt(new Date());          // (LocalDateTime.now(ZoneId.of("+9")))

            guestBookRepository.save(guestBookSelect);
        });
    }

    // throw new ApplicationException(HttpStatus.valueOf(404));
    @Override
    public GuestBookDto.ResponseList getGuestBook(String date) {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(23, 59, 59));

        Date start = Timestamp.valueOf(startTime);
        Date end = Timestamp.valueOf(endTime);

        Optional<List<GuestBook>> guestBookList = guestBookRepository.findAllByCreateAtBetween(start, end);
        System.out.println(start.toString());
        System.out.println(end.toString());
        List<GuestBookDto> guestBookDtoList = new ArrayList<>();
        if(!guestBookList.isPresent()) return null;
        for(GuestBook g : guestBookList.get()){
            GuestBookDto guestBookDto  = g.convertToDto();

            Optional<User> user = userRepository.findById(guestBookDto.getUserId());
            guestBookDto.setNickname(user.get().getNickname());

            guestBookDtoList.add(guestBookDto);
        }

        GuestBookDto.ResponseList res = GuestBookDto.ResponseList.builder()
                .data(guestBookDtoList)
                .build();

        return res;
    }
}
