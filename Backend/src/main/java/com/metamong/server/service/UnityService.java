package com.metamong.server.service;

public interface UnityService {
    void setCharacter(int userId, String unityCharacter);
    void setRoom(int userId, String unityRoom);
    void setRoomPopulation(int userId, int unityRoomPopulation);
}
