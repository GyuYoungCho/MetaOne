package com.metamong.server.controller;

import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.User;
import com.metamong.server.repository.UserRepository;
import com.metamong.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestBody @ApiParam(value="회원수정 정보", required = true) Object updateInfo, HttpServletRequest request
        ) throws IOException{
        int userId = (Integer) request.getAttribute("userId");
        // 닉네임이랑 비번만 바꾸기 가능요
//        if (userService.isExistEmail(updateInfo.getEmail()))
//            return ResponseEntity.status(409).build();

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @DeleteMapping("")
    @ApiOperation(value="회원탈퇴", notes = "사용자가 자신의 정보를 삭제하고 탈퇴한다.")
    public ResponseEntity delete() throws IOException{

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @param loginInfo : 로그인 정보
     * @return
     * @throws IOException
     */
    @PostMapping("login")
    @ApiOperation(value="로그인", response =UserDto.Response.class)
    public ResponseEntity<UserDto.Response> login(
            @RequestBody  @ApiParam(value="로그인 정보", required = true) UserDto.LoginRequest loginInfo
            ) throws InterruptedException, IOException{

        // 유저 서비스 로그인 로직
        return ResponseEntity.status(200).build();
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
        if (type == "email"){
            if (userService.isExistEmail(data)) return new ResponseEntity(HttpStatus.valueOf(400));
            return new ResponseEntity(HttpStatus.valueOf(200));
       // type Nickname 중복검사
        }else if(type == "nickname"){
            if (userService.isExistNickname(data)) return new ResponseEntity(HttpStatus.valueOf(400));
            return new ResponseEntity(HttpStatus.valueOf(200));

        }else return ResponseEntity.status(400).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @PostMapping("email")
    @ApiOperation(value="이메일 인증")
    public ResponseEntity checkEmail(
            @RequestBody @ApiParam(value="이메일", required = true) UserDto.TokenRequest tokenReq
            ) throws IOException{


        return ResponseEntity.status(200).build();
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
    public ResponseEntity<UserDto.Response> loginkakao(@RequestBody Map<String, String> payload) throws IOException{

        System.out.println("email >>>> " + payload.get("email"));
        System.out.println("name >>>> " + payload.get("name"));

        String email = payload.get("email");
        String name = payload.get("name");

        if(!userService.isExistEmail(email)) {
            // 이메일 없으면 최초 로그인이므로 회원 정보 DB에 등록
            userService.kakaoRegister(email, name);
        }

        // 이메일 이미있으면 가입된 유저이므로 유저 정보 가져와서 넘겨줌
        UserDto.Response res = userService.login(email);

        return ResponseEntity.ok().body(res);
    }

    /***
     *
     * @param nickname : 다른 사용자의 닉네임
     * @return
     * @throws IOException
     */
    @GetMapping("{nickname}")
    @ApiOperation(value="다른 사용자의 정보 조회")
    public ResponseEntity<UserDto.userInfoResponse> userinfo( @PathVariable String nickname) throws IOException{

        UserDto.userInfoResponse res = userService.getUserInfo(nickname);

        return ResponseEntity.ok().body(res);
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
    public ResponseEntity character(HttpServletRequest request) throws IOException{

        int userId = (Integer) request.getAttribute("userId");
        // token에 저장되어 있는 userId를 service로 보내줌
        UserDto.characterResponse res = userService.getCharacter(userId);

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

