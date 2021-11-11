package com.metamong.server.service;

import com.metamong.server.entity.Characters;
import com.metamong.server.entity.User;
import com.metamong.server.repository.CharactersRepository;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnityServiceImpl implements UnityService{

    private final CharactersRepository charactersRepository;
    private final UserRepository userRepository;

    @Autowired
    public UnityServiceImpl(CharactersRepository charactersRepository, UserRepository userRepository){
        this.charactersRepository = charactersRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void setCharacter(int userId, String unityCharacter) {

        Optional<User> user = userRepository.findById(userId);
//        System.out.println(unityCharacter);
        Optional<Characters> character = charactersRepository.findByName(unityCharacter);
        character.ifPresent(select -> {
            user.get().setCharacter(select);
            userRepository.save(user.get());
        });
    }
}
