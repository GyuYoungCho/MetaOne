package com.metamong.server.service;

import com.metamong.server.entity.Characters;
import com.metamong.server.entity.Room;
import com.metamong.server.entity.User;
import com.metamong.server.repository.CharactersRepository;
import com.metamong.server.repository.RoomRepository;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnityServiceImpl implements UnityService{

    private final CharactersRepository charactersRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public UnityServiceImpl(CharactersRepository charactersRepository, UserRepository userRepository, RoomRepository roomRepository){
        this.charactersRepository = charactersRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void setCharacter(int userId, int unityCharacter) {

        Optional<User> user = userRepository.findById(userId);

        Optional<Characters> character = charactersRepository.findById(unityCharacter);
        character.ifPresent(select -> {
            user.get().setCharacter(select);
            userRepository.save(user.get());
        });
    }

    @Override
    public Room setRoom(int userId, String unityRoom) {
        Room room = Room.builder()
                .name(unityRoom)
                .build();

        Room roomRet = roomRepository.saveAndFlush(room);
        System.out.println("roomRet: " + roomRet.getId());
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(select -> {
            select.setRoom(roomRet);

            userRepository.save(select);
        });
        return roomRet;
    }
}
