package com.metamong.server.controller;

import com.metamong.server.dto.UserDto;
import com.metamong.server.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

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

        // 유저 서비스 로직

        return ResponseEntity.status(200).build();
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
            @RequestBody @ApiParam(value="회원수정 정보", required = true) Object updateInfo
        ) throws IOException{

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

        return ResponseEntity.status(200).build();
    }

    /***
     *
     * @return
     * @throws IOException
     */
    @PostMapping("email")
    @ApiOperation(value="이메일 인증")
    public ResponseEntity email(
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
    public ResponseEntity loginkakao() throws IOException{

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

