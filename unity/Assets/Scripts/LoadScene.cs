using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

using Photon.Pun;
using Photon.Realtime;

public class LoadScene : MonoBehaviourPunCallbacks
{
    //    private Text[] timeText = { "05", "00" };
    int IsOut;
    private float LimitTime = 300;
    public Text text_Timer;
    private bool startTimer = false;
    private int min, sec;

    string roomName;
    bool isRejoin=false;

    private void Start()
    {
        roomName = PlayerPrefs.GetString("roomTitle");
    }

    void Update()
    {
        // 미션 클리어 패널이 null 아닐 때 타이머 멈추기
        if (GameObject.Find("MissionClear") != null) startTimer = false;

        // 타이머
        if (startTimer)
        {
            LimitTime -= Time.deltaTime;

            min = (int)LimitTime / 60;
            sec = ((int)LimitTime - min * 60) % 60;

            if (LimitTime < 60) text_Timer.color = Color.red;   // 시간이 1분 미만 남았을 경우

            if(min<= 0 && sec<=0)
            {
                text_Timer.text = "00 : 00";
            }
            else
            {
                if(sec>=60)
                {
                    min += 1;
                    sec -= 60;
                }
                else
                {
                    text_Timer.text = "0" + min.ToString() + " : " + sec.ToString();
                }
            }
        }
    }

    public void outRoom()
    {
        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        // false 매개변수 주지 않으면 기본값 true
        // true일 때 -> 플레이어는 방의 플레이어 목록에 남아 있고 나중에 돌아올 수 있습니다. (플레이어수 유지)
        // 따라서 false로 바꾸고 플레이어 목록에서 지워줘야 함.
        PhotonNetwork.LeaveRoom(false);
    }

    public void ChangeFire()
    {
        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        // 화면 동기화 끊어주고 LoadLevel로 이동해야만 같이 이동 XX
        PhotonNetwork.AutomaticallySyncScene = false;
        PhotonNetwork.IsMessageQueueRunning = false;
        PhotonNetwork.LoadLevel("Fire");
    }

    public void quitMission()
    {
        // 방에서 나가기 위해 연결 끊어줌
        PhotonNetwork.Disconnect();
    }
    

    public void startMission()
    {
        // 미션 시작 안내 지우기
        Destroy(GameObject.Find("BeforeStart"));

        // 미션 내용과 그만두기 버튼 active
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);
        GameObject.Find("Canvas").transform.Find("Button").gameObject.SetActive(true);

        // 메인카메라에 movingCharacter 스크립트 추가
        GameObject mission = GameObject.Find("MainCamera");

        // 타이머 시작
        GameObject.Find("Canvas").transform.Find("Timer").gameObject.SetActive(true);
        startTimer = true;
    }

    public void clearMission()
    {
        // 결과 서버에 전송
        //        text_Timer.text

        // 메인 맵으로 이동
        quitMission();
    }

    public override void OnDisconnected(DisconnectCause cause)
    {
        // 연결이 성공적으로 끊어지면 재접속 (같은방으로 접속 됨)
        Debug.Log("연결끊기");
        isRejoin = true;
        Debug.Log(PhotonNetwork.ReconnectAndRejoin());
    }

    public override void OnJoinedRoom()
    {
        if(isRejoin)
        {
            // 방에 접속되면 Main Scene 다시 로드
            Debug.Log("ReJoined Room !!!");
            // photonNetwork의 데이터 통신을 잠깐 정지 시켜준다. 
            // gamemanager에서 creatTank하고 나면 다시 연결시킨다
            PhotonNetwork.IsMessageQueueRunning = false;
            SceneManager.LoadScene("Main");
        }
    }

    public override void OnLeftRoom()
    {
        PhotonNetwork.LoadLevel("ChooseRoom");
        //base.OnLeftRoom();
    }

}
