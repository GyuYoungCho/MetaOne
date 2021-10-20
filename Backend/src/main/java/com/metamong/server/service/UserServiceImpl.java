package com.metamong.server.service;

import com.metamong.server.dto.UserDto;
import com.metamong.server.dto.encode.Encoder;
import com.metamong.server.entity.User;
import com.metamong.server.repository.UserRepository;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final String ENCODE_ID = "bcrypt";
    private static final Map<String, PasswordEncoder> encoders = Encoder.getEncoder();      // 인코더 : 여러 타입의 암호화 방식을 저장

    @Override
    public int register(UserDto.RegisterRequest registerInfo) {
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(ENCODE_ID, encoders);
        String encPassword = passwordEncoder.encode(registerInfo.getPassword());
        // 암호화 확인 작업
        //if(!passwordEncoder.matches(registerInfo.getPassword(), encPassword)) throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "비밀번호 암호화 중 불일치 오류");
        User user = new User(registerInfo.getEmail(), encPassword, registerInfo.getName(), registerInfo.getNickname());

        return userRepository.save(user).getId();
    }


    @Override
    public boolean isExistEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public boolean isExistNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);
        return user.isPresent();
    }

    @Override
    public UserDto.Response login(UserDto.LoginRequest loginReq) {

        return null;
    }

    @Override
    public String TokenGeneration(int userId, String receiverEmail, String url) {

        return null;
    }
}
