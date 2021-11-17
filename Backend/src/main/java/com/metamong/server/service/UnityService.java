package com.metamong.server.service;

import com.metamong.server.entity.Room;

public interface UnityService {
    void setCharacter(int userId, int unityCharacter);
    Room setRoom(int userId, String unityRoom);
}
