package com.metamong.server.controller;

import com.metamong.server.dto.*;
import com.metamong.server.service.AttendanceService;
import com.metamong.server.service.CertificateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/edu")
public class EduController {

    private AttendanceService attendanceService;

    public EduController(AttendanceService attendance){
        this.attendanceService = attendance;
    };

    @Autowired
    private CertificateService certificateService;

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
     * @param education : 교육명
     * @param request : Client 요청 정보
     * @return
     */
    @GetMapping("/certificate")
    @ApiOperation(value = "교육 증명서 조회")
    public ResponseEntity<EducationDto.EduResponse> getCertificate(@RequestParam String education, HttpServletRequest request){

        int userId = (Integer) request.getAttribute("userId");

        EducationDto.EduResponse eduResponse = certificateService.getCertificate(education, userId);

        return ResponseEntity.ok().body(eduResponse);
    }

    /**
     *
     * @param eduReq : 교육명, 통과시간
     * @param request : Client 요청 정보
     * @return
     */
    @PutMapping("/rank")
    @ApiOperation(value = "미션 기록 저장 및 수정")
    public ResponseEntity updateCertificate(@RequestBody EducationDto.EduRequest eduReq, HttpServletRequest request){

        int userId = (Integer) request.getAttribute("userId");

        certificateService.updateCertificate(eduReq, userId);

        return ResponseEntity.status(200).build();
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
