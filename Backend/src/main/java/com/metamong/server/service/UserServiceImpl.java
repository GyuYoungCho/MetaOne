package com.metamong.server.service;

import com.metamong.server.dto.UserDto;
import com.metamong.server.dto.encode.Encoder;
import com.metamong.server.entity.User;
import com.metamong.server.exception.ApplicationException;
import com.metamong.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
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
        System.out.println("있나 확인해보았는데.. "+user );
        return user.isPresent();
    }

    @Override
    public UserDto.LoginRes login(UserDto.LoginRequest loginReq) {
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(ENCODE_ID, encoders);
        Optional<User> user = userRepository.findByEmail(loginReq.getEmail());

        if (!user.isPresent()) throw new ApplicationException(HttpStatus.valueOf(401), "일치하는 이메일이 없습니다.");
        if(!passwordEncoder.matches(loginReq.getPassword(), user.get().getPassword())) throw new ApplicationException(HttpStatus.valueOf(401), "비밀번호가 일지하지 않습니다.");

        User loginuser = user.orElseThrow(IllegalArgumentException::new);;
        UserDto.LoginRes login = new UserDto.LoginRes();
        login.setName(loginuser.getName());
        login.setNickname(loginuser.getNickname());
        login.setEmail(loginuser.getEmail());
        login.setId(loginuser.getId());
        return login;
    }

    @Override
    public String TokenGeneration(int userId, String receiverEmail, String url) {

        return null;
    }
    public void validatePassword( String password){
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$";
        if(!password.matches(pattern)) throw new ApplicationException(HttpStatus.valueOf(400), "비밀번호 형식 오류");
    }

    @Override
    public void updatePassword(UserDto.UpdateRequest updateInfo, HttpServletRequest request) {
        Optional<User> user = userRepository.findById((int) request.getAttribute("userId"));
        if(!user.isPresent()) throw new ApplicationException(HttpStatus.valueOf(401), "회원 정보가 없습니다.");

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(ENCODE_ID, encoders);
        if(!passwordEncoder.matches(updateInfo.getOriginPassword(), user.get().getPassword())) throw new ApplicationException(HttpStatus.valueOf(401), "비밀번호 불일치");

        user.ifPresent(userSelect -> {
            userSelect.setPassword(passwordEncoder.encode(updateInfo.getNewPassword()));
            userRepository.save(userSelect);
        });
    }

    @Override
    public void updateNickname(UserDto.UpdateRequest updateInfo, HttpServletRequest request) {
        Optional<User> user = userRepository.findById((int) request.getAttribute("userId"));
        if(!user.isPresent()) throw new ApplicationException(HttpStatus.valueOf(401), "회원 정보가 없습니다.");

        user.ifPresent(userSelect -> {
            userSelect.setNickname(updateInfo.getNickname());
            userRepository.save(userSelect);
        });
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                                .orElseThrow(IllegalArgumentException::new);
        if(user != null) userRepository.delete(user);
}   }