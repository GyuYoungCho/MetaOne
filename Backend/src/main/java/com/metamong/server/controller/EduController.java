package com.metamong.server.controller;

import com.metamong.server.dto.EducationDto;
import com.metamong.server.dto.MyAttendDto;
import com.metamong.server.dto.RankDto;
import com.metamong.server.entity.Room;
import com.metamong.server.service.AttendanceService;
import com.metamong.server.service.CertificateService;
import com.metamong.server.service.UnityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/edu")
public class EduController {

    private final AttendanceService attendanceService;
    private final UnityService unityService;

    @Autowired
    public EduController(AttendanceService attendance, UnityService unityService){
        this.attendanceService = attendance;
        this.unityService = unityService;

    };

    @Autowired
    private CertificateService certificateService;

    // 캐릭터 파일 이름 저장
    @PostMapping("/character")
    public ResponseEntity setCharacter(@RequestBody Map<String, String> unityCharacter, HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");

        unityService.setCharacter(userId, Integer.parseInt(unityCharacter.get("unityCharacter")));

        return ResponseEntity.ok().build();
    }

    // 방 이름 저장
    @PostMapping("/room")
    public ResponseEntity setRoom(@RequestBody Map<String, String> unityRoom, HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        System.out.println("방이름 : " + unityRoom.get("unityRoom"));
        Room room = unityService.setRoom(userId, unityRoom.get("unityRoom"));            // 유저 ID, 방이름
        return ResponseEntity.ok().body(room);
    }

    // 미션 클리어 시간 저장
    @PostMapping("/time")
    public ResponseEntity setMissionClearTime(@RequestBody Map<String, String> unity, HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");

        System.out.println(unity.get("unityEducationTime"));
        System.out.println(unity.get("unityEducation"));
        certificateService.setMissionClearTime(userId, Integer.parseInt(unity.get("unityEducationTime")), unity.get("unityEducation"));

        return ResponseEntity.ok().build();
    }
    /**
     * 교육 내역 조회
     * @return
     */
    @GetMapping("/attendance")
    @ApiOperation(value = "나의 교육 내역 조회")
    public ResponseEntity<MyAttendDto.ResponseList> getMyAttendance(HttpServletRequest request){

        int userId = (Integer) request.getAttribute("userId");

        return ResponseEntity.ok().body(attendanceService.getMyAttendance(userId));
    }

    /**
     * 교육 수강 완료
     * @param education : 교육 명
     * @param request : Client 요청 정보
     * @return
     */
    @PostMapping("/attendance")
    @ApiOperation(value = "교육 수강 완료")
    public ResponseEntity registerAttendance(@RequestParam String education, HttpServletRequest request){

        int userId = (Integer) request.getAttribute("userId");

        attendanceService.registerAttendance(education, userId);

        return ResponseEntity.status(201).build();
    }

    /**
     *
     * @param education : 교육 명
     * @param request : Client 요청 정보
     * @return
     */
    @GetMapping("/attendance/{education}")
    @ApiOperation(value = "특정 교육 수강 여부 조회")
    public ResponseEntity getAttendance(@PathVariable("education") String education, HttpServletRequest request){

        int userId = (Integer) request.getAttribute("userId");

        Map<String, Boolean> map = attendanceService.isAttended(education, userId);

        return ResponseEntity.ok().body(map);
    }

    /**
     *
     * @param education : 교육 명
     * @return
     */
    @GetMapping("/rank")
    @ApiOperation(value = "유저 랭킹(미션 기록) 조회")
    public ResponseEntity<RankDto.ResponseList> getRank(@RequestParam String education){

        RankDto.ResponseList responseList = certificateService.getRank(education);

        return ResponseEntity.ok().body(responseList);
    }

}
