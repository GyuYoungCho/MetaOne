using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

using Photon.Pun;
using Photon.Realtime;

using System.Runtime.InteropServices;

public class LoadScene : MonoBehaviourPunCallbacks
{
    [DllImport("__Internal")]
    private static extern void UnityEducationTimeHook(int eduTime);

    [DllImport("__Internal")]
    private static extern void UnityObjectHook(string obj);

    [DllImport("__Internal")]
    private static extern void UnityEducationNameHook(string eduName);

    int IsOut;
    private float LimitTime = 120;  // 2분 시간제한
    public Text text_Timer;
    private bool startTimer = false;
    private int min, sec;

    string roomName;
    bool isRejoin=false;
    bool isChange = false;

    int BgmState;
    float BgmVolume;

    private void Start()
    {
        roomName = PlayerPrefs.GetString("roomTitle");
        BgmState = PlayerPrefs.GetInt("BgmState");
        BgmVolume = PlayerPrefs.GetFloat("volume");

        // Fire, Earthquake Scene일 때
        // 현재 씬의 BGM 상태를 로비에서 설정한 상태와 동기화시켜줌
        if(SceneManager.GetActiveScene().name != "Main")
        {
            if (BgmState == 1)
                GameObject.Find("MainCamera").GetComponent<AudioSource>().volume = BgmVolume;
            else
                GameObject.Find("MainCamera").GetComponent<AudioSource>().Pause();
        }
    }

    void Update()
    {
        // 미션 클리어 패널이 null 아닐 때 타이머 멈추기 (미션 성공 or 실패)
        if (GameObject.Find("MissionClear") != null) startTimer = false;

        // 타이머
        if (startTimer)
        {
            LimitTime -= Time.deltaTime;

            min = (int)LimitTime / 60;
            sec = ((int)LimitTime - min * 60) % 60;

            // 시간이 1분 미만 남았을 경우 색상 변경
            if (LimitTime < 60) text_Timer.color = Color.red;

            // 타임 아웃
            if(min<= 0 && sec<=0)
            {
                text_Timer.text = "00 : 00";

                // 더이상 움직이지 않도록 FirstPersonCam 컴포넌트 삭제
                Destroy(GetComponent<firstPersonCam>());

                // 그만두기 버튼 hide
                // Null Exception 뜨지 않도록 try-catch 처리
                try { GameObject.Find("Button").gameObject.SetActive(false); }
                catch { }

                // MissonFail 패널 show
                GameObject.Find("Canvas").transform.Find("MissionFail").gameObject.SetActive(true);
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

    // 방 나가기 버튼
    public void outRoom()
    {
        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        // false 매개변수 주지 않으면 기본값 true
        // true일 때 -> 플레이어는 방의 플레이어 목록에 남아 있고 나중에 돌아올 수 있습니다. (플레이어수 유지)
        // 따라서 false로 바꾸고 플레이어 목록에서 지워줘야 함.
        PhotonNetwork.LeaveRoom(false);
    }

    // 캐릭터 변경 onclick
    public void changeCharacter()
    {
        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        isChange = true;
        PlayerPrefs.SetInt("isChange", 1);

        // false 매개변수 주지 않으면 기본값 true
        // 해당 룸으로 다시 돌아올 것이므로 방의 플레이어 목록에 남아 있게 함. (플레이어수 유지)
        PhotonNetwork.LeaveRoom();
    }

    // 화재 미션하기 -> Fire Scene으로 이동
    public void ChangeFire()
    {
        // unity -> front로 교육명 전달
        UnityEducationNameHook("fire");

        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        // 화면 동기화 끊어주고 LoadLevel로 이동해야만 같이 이동 XX
        PhotonNetwork.AutomaticallySyncScene = false;
        PhotonNetwork.IsMessageQueueRunning = false;
        PhotonNetwork.LoadLevel("Fire");
    }

    // 지진 미션하기 -> Earthquake Scene으로 이동
    public void ChangeEarthquake()
    {
        // unity -> front로 교육명 전달
        UnityEducationNameHook("earthquake");

        // 연결된 모든 유저들에게서 내 캐릭터 삭제
        PhotonNetwork.DestroyPlayerObjects(PhotonNetwork.LocalPlayer);

        // 화면 동기화 끊어주고 LoadLevel로 이동해야만 같이 이동 XX
        PhotonNetwork.AutomaticallySyncScene = false;
        PhotonNetwork.IsMessageQueueRunning = false;
        PhotonNetwork.LoadLevel("Earthquake");
    }

    // 미션 그만두기 or 미션 성공 후 이동
    public void quitMission()
    {
        // 방에 재접속 위해 연결 끊어줌 -> OnDisconnected callback
        PhotonNetwork.Disconnect();
        isRejoin = true;
    }

    // Fire & Earthquake Scene에서 미션시작 onclick
    public void startMission()
    {
        // 미션 시작 안내 지우기
        Destroy(GameObject.Find("BeforeStart"));

        // 미션 내용과 그만두기 버튼 active
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);
        GameObject.Find("Canvas").transform.Find("Button").gameObject.SetActive(true);
        
        // 타이머 시작
        GameObject.Find("Canvas").transform.Find("Timer").gameObject.SetActive(true);
        startTimer = true;
    }

    public void clearMission()
    {
        // unity -> front 미션 완료 타임 전송
        UnityEducationTimeHook((int)(120-LimitTime));

        // 메인 맵으로 이동
        quitMission();
        isRejoin = true;
    }

    public override void OnDisconnected(DisconnectCause cause)
    {
        // 연결이 성공적으로 끊어지면 재접속 (같은방으로 접속 됨)
        Debug.Log(PhotonNetwork.ReconnectAndRejoin());
    }

    public override void OnJoinedRoom()
    {
        // 방 재접속인 경우
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
        // 캐릭터 선택위해 방 떠나는 경우
        if (isChange)
        {
            isChange = false;
            PhotonNetwork.LoadLevel("ChooseCharacter");
        }
        // 다른방 이동 위해 방 나가기 버튼으로 방 떠나는 경우
        else if (!isRejoin)
        {
            // unity -> front 방 나갈 때 메세지 전송
            UnityObjectHook("roomOut");
            PhotonNetwork.LoadLevel("ChooseRoom");
        }

        //base.OnLeftRoom();
    }

    // rejoin 시 방에 인원이 1명이였기 때문에 방이 없어진 경우
    public override void OnJoinRoomFailed(short returnCode, string message)
    {
        PhotonNetwork.CreateRoom(roomName, new RoomOptions { MaxPlayers = 4, PlayerTtl = 60000 });
        PhotonNetwork.RejoinRoom(roomName);
        //base.OnJoinRoomFailed(returnCode, message);
    }

}
