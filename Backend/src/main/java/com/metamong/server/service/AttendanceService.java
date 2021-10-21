package com.metamong.server.service;

import com.metamong.server.dto.MyAttendDto;

public interface AttendanceService {
    MyAttendDto.ResponseList getMyAttendance(int userId);
    void registerAttendance(String education, int userId);
}
