package com.metamong.server.controller;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.metamong.server.config.RedisUtil;
import com.metamong.server.dto.EmailDto;
import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.User;
import com.metamong.server.exception.ApplicationException;
import com.metamong.server.repository.UserRepository;
import com.metamong.server.service.EmailSenderService;
import com.metamong.server.service.JwtService;
import com.metamong.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtService jwtService;

    @Value("${token.accesstoken}")
    private String accessToken;

    @Value("${token.refreshtoken}")
    private String refreshToken;

    /***
     *
     * @param registerInfo : 회원가입 정보
     * @return
     * @throws IOException
     */
    @PostMapping("")
    @ApiOperation(value="회원가입", notes = "사용자가 회원가입을 시도한다.")
    public ResponseEntity <Map<String, Integer>> resgister(
            @RequestBody  @ApiParam(value="회원가입 정보", required = true)
            @Valid UserDto.RegisterRequest registerInfo, BindingResult bindingResult
            ) throws IOException {

        if (bindingResult.hasErrors())
            return ResponseEntity.status(400).build();

        if (userService.isExistEmail(registerInfo.getEmail()))
            return ResponseEntity.status(409).build();

        userService.validatePassword(registerInfo.getPassword());

        Map<String, Integer> map = new HashMap<>();
        map.put("id", userService.register(registerInfo));
        return new ResponseEntity<>(map, HttpStatus.valueOf(201));
    }

    /***
     *
     * @param updateInfo : 회원수정 정보
     * @return
     * @throws IOException
     */
    @PutMapping("")
    @ApiOperation(value="회원정보 수정", notes = "사용자가 자신의 정보를 수정한다.")
    public ResponseEntity update(
            @RequestBody @ApiParam(value="회원수정 정보", required = true) UserDto.UpdateRequest updateInfo, HttpServletRequest request
        ) throws IOException{
        // int userId = (Integer) request.getAttribute("userId");
        if(updateInfo.getOriginPassword() == null) return ResponseEntity.status(401).build();
        if(updateInfo.getNickname() != null){
            userService.updateNickname(updateInfo, request);
        }
        if(updateInfo.getNewPassword() != null){
            userService.validatePassword(updateInfo.getNewPassword());
            userService.updatePassword(updateInfo, request);
        }
        if(updateInfo.getNewPassword() == null & updateInfo.getNickname() == null)
            throw new ApplicationException(HttpStatus.valueOf(401), "수정할 정보가 없습니다.");

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @DeleteMapping("")
    @ApiOperation(value="회원탈퇴", notes = "사용자가 자신의 정보를 삭제하고 탈퇴한다.")
    public ResponseEntity delete(HttpServletRequest request) throws IOException{
        int userId = (int) request.getAttribute("userId");
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
            userRepository.delete(user);
            return ResponseEntity.status(200).build();
        }else{
            throw new ApplicationException(HttpStatus.valueOf(401), "삭제할 정보가 없습니다.");
        }
    }

    /***
     *
     * @param loginInfo : 로그인 정보
     * @return
     * @throws IOException
     */
    @PostMapping("login")
    @ApiOperation(value="로그인", response =UserDto.Response.class)
    public ResponseEntity login(
            @RequestBody  @ApiParam(value="로그인 정보", required = true) UserDto.LoginRequest loginInfo
            ) throws InterruptedException, IOException{
        UserDto.LoginRes loginRes = userService.login(loginInfo);
        Map<String, Object> map = jwtService.createToken(loginRes.getId());
        System.out.println("map : "+ map.get(accessToken));

        HttpHeaders resHeader = new HttpHeaders();

        resHeader.set(accessToken, (String) map.get(accessToken));
        System.out.println("resHeader : "+ resHeader.get(accessToken));
        resHeader.set(refreshToken, (String) map.get(refreshToken));
        System.out.println("resHeader : "+ resHeader.get(refreshToken));

        //if(loginInfo.getFirebaseToken() != null) fcmService.save(myRes, myReq.getFirebaseToken());

        return ResponseEntity.ok().headers(resHeader).body(loginRes);
    }

    /***
     *
     * @param type : 닉네임 or 이메일
     * @param data : 닉네임이나 이메일 정보
     * @return
     * @throws IOException
     */
    @GetMapping("duplicate")
    @ApiOperation(value="중복확인", notes="이메일/닉네임 중복확인")
    public ResponseEntity duplicate(
            @RequestParam @ApiParam(value="닉네임 or 이메일", required = true) String type, @RequestParam @ApiParam(value="닉네임이나 이메일 정보", required = true) String data
            ) throws IOException{
        // type Email 중복검사
        System.out.println("중복검사 시작합니다... "+ type +" / "+ data);
        if (type.equals("email")){
            if (userService.isExistEmail(data)) return new ResponseEntity(HttpStatus.valueOf(400));
            return new ResponseEntity(HttpStatus.valueOf(200));
       // type Nickname 중복검사
        }else if(type.equals("nickname")){
            if (userService.isExistNickname(data)) return new ResponseEntity(HttpStatus.valueOf(400));
            return new ResponseEntity(HttpStatus.valueOf(200));
        }else 
            System.out.println("수정할 정보를 입력하세요");
            return ResponseEntity.status(400).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @PostMapping("email")
    @ApiOperation(value="이메일 인증")
    public ResponseEntity checkEmail(
            @RequestBody @ApiParam(value="이메일", required = true) UserDto.EmailRequest emailReq
            ) throws IOException{
        System.out.println("----이메일 인증-----");
        EmailDto emailDto = new EmailDto();
        emailDto.setCode(emailSenderService.createKey());
        emailDto.setToEmail(emailReq.getEmail());
        emailDto.setTitle("메타몽 서비스 인증메일입니다.");
        emailDto.setValidTime(30 * 60 * 1000L);

        emailSenderService.sendEmail(emailDto);
        System.out.println("----이메일 인증 보냄----");
        return ResponseEntity.status(200).build();
    }

    @PostMapping("email-check")
    @ApiOperation(value="이메일 인증 확인")
    public boolean checkEmailConfirm(
            @RequestBody @ApiParam(value="이메일 인증 확인", required = true) UserDto.EmailResponse emailRes
    ) throws IOException{

        String email = redisUtil.getData(emailRes.getCode());
        if (email == null) { // email이 존재하지 않으면, 유효 기간 만료이거나 코드 잘못 입력
            throw new ApplicationException(HttpStatus.valueOf(401), "이메일 인증이 만료되었거나, 코드가 맞지 앉습니다.ㄴ");
        }
        if (email.equals(emailRes.getEmail())) return true;

        return false;
    }



    /***
     *
     * @param firebaseToken : 로그인 토큰
     * @return
     * @throws IOException
     */
    @DeleteMapping("login")
    @ApiOperation(value="로그아웃")
    public ResponseEntity logout(
            @RequestParam @ApiParam(value="Token", required = true) String firebaseToken
            ) throws IOException{

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @PostMapping("login-kakao")
    @ApiOperation(value="카카오톡 로그인")
    public ResponseEntity loginkakao(@RequestBody Map<String, String> payload) throws IOException{

        System.out.println("email >>>> " + payload.get("email"));
        System.out.println("name >>>> " + payload.get("name"));
        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @param nickname : 다른 사용자의 닉네임
     * @return
     * @throws IOException
     */
    @GetMapping("{nickname}")
    @ApiOperation(value="다른 사용자의 정보 조회")
    public ResponseEntity userinfo( @PathVariable String nickname) throws IOException{

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @param fileUrl : 캐릭터 file url
     * @return
     * @throws IOException
     */
    @PutMapping("charater")
    @ApiOperation(value="사용자 캐릭터 선택")
    public ResponseEntity selectcharacter(
            @RequestBody @ApiParam(value = "캐릭터 file url", required = true) Object fileUrl
            ) throws IOException{

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @GetMapping("character")
    @ApiOperation(value="현재 사용자 캐릭터 조회")
    public ResponseEntity character() throws IOException{

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @GetMapping("characters")
    @ApiOperation(value="모든 캐릭터 조회")
    public ResponseEntity allcharacters() throws IOException{

        return ResponseEntity.status(200).build();
    }
}

