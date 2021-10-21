package com.metamong.server.service;

import com.metamong.server.dto.MyAttendDto;

import java.util.Map;

public interface AttendanceService {
    MyAttendDto.ResponseList getMyAttendance(int userId);
    void registerAttendance(String education, int userId);
    Map<String, Boolean> isAttended(String education, int userId);
}
