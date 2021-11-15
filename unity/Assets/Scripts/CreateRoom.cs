using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Threading;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

using System;   // convert

public class CreateRoom : MonoBehaviourPunCallbacks
{
    string characterData, characterName;
    int IsOut;
    string title;
    byte limit = 4;
    RoomInfo[] roomInfoList;

    public GameObject room;
    public Transform gridTr;

    private void Awake()
    {
        // photon1과 photon2로 바뀌면서 달라진점 (같은방 동기화)
        PhotonNetwork.AutomaticallySyncScene = true;
    }

    // Start is called before the first frame update
    void Start()
    {
        // 전달받은 데이터 불러오기
        characterData = PlayerPrefs.GetString("character");
        characterName = PlayerPrefs.GetString("characterN");
        IsOut = PlayerPrefs.GetInt("IsOut");

        if (PhotonNetwork.IsConnected)
        {
            Debug.Log("still connected!!");
            PhotonNetwork.JoinLobby();
        }
    }

    // Update is called once per frame
    void Update()
    {
        //if (IsOut == 1)
        //{
        //    PhotonNetwork.LeaveRoom();
        //    IsOut = 0;
        //    PlayerPrefs.SetInt("IsOut", IsOut);
        //}
        // 방 목록 가져오기
        //        roomInfoList = PhotonNetwork.GetRoomList(); // 없어짐 OnRoomListUpdate 이걸로 해야함
        // ILobbyCallbacks.OnRoomListUpdate(List < RoomInfo > roomList)

    }

    // 방만들기 버튼 click
    public void createNewRoom()
    {
        // 방 선택 패널 비활성화
        GameObject.Find("Panel").SetActive(false);

        // 방 생성 패널 활성화
        GameObject.Find("Canvas").transform.Find("MakeRoom").gameObject.SetActive(true);

        GameObject inputTitle = GameObject.Find("Title");
        var inputT = inputTitle.GetComponent<InputField>();
        var se = new InputField.SubmitEvent();
        se.AddListener(SubmitName);
        inputT.onEndEdit = se;

        Debug.Log(inputT.onEndEdit);
    }

    public override void OnConnectedToMaster()
    {
        Debug.Log("Connect To Master");
        PhotonNetwork.JoinLobby();
    }

    public override void OnJoinedLobby()
    {
        Debug.Log("Joined Lobby");
    }

    public override void OnJoinedRoom()
    {
        Debug.Log("Joined Room !!!");
        // photonNetwork의 데이터 통신을 잠깐 정지 시켜준다. 
        // gamemanager에서 creatTank하고 나면 다시 연결시킨다
        PhotonNetwork.IsMessageQueueRunning = false;

        // 캐릭터 데이터 저장
        PlayerPrefs.SetString("character", characterData);
        PlayerPrefs.SetString("characterN", characterName);
        Debug.Log("createRoom    " + characterName);

        SceneManager.LoadScene("Main");
    }

    private void SubmitName(string arg0)
    {
        Debug.Log(arg0);
        title = arg0;
    }

    // 취소하기 버튼 click
    public void cancelCreateRoom()
    {
        // 방 생성 패널 비활성화
        GameObject.Find("MakeRoom").SetActive(false);

        // 방 선택 패널 활성화
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);
    }

    public void setInputTitle(string _data)
    {
        title = _data;
    }

    // 생성하기 버튼 click
    public void onClickCreateRoom()
    {
        // 방 생성 패널 비활성화
        GameObject.Find("MakeRoom").SetActive(false);

        // 방 선택 패널 활성화
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);

        // 방 생성
        PhotonNetwork.CreateRoom(title, new RoomOptions { MaxPlayers = 4, PlayerTtl = 60000 });
    }

    // 방 정보
    public override void OnRoomListUpdate(List<RoomInfo> roomList)
    {
        Debug.Log("방 목록 로드");

        foreach(GameObject obj in GameObject.FindGameObjectsWithTag("ROOM"))
        {
            Destroy(obj);
        }
        foreach(RoomInfo roomInfo in roomList)
        {
            // 방 나가면 한번 더 OnRoomListUpdate가 호출되면서 나간 방의 Instantiate가 null이 됨
            GameObject _room;
            try
            {
                _room = Instantiate(room, gridTr);
            }
            catch
            {
                PhotonNetwork.LoadLevel("ChooseRoom");
                continue;
            }

            Image roomImg = _room.GetComponent(typeof(Image)) as Image;
            roomImg.enabled = true;
            Button roomBtn = _room.GetComponent(typeof(Button)) as Button;
            roomBtn.enabled = true;
            Text roomTxt = _room.GetComponentInChildren(typeof(Text)) as Text;
            roomTxt.enabled = true;

            RoomData roomData = _room.GetComponent<RoomData>();
            roomData.roomName = roomInfo.Name;
            roomData.maxPlayer = roomInfo.MaxPlayers;
            roomData.playerCount = roomInfo.PlayerCount;
            roomData.UpdateInfo();
            roomData.GetComponent<Button>().onClick.AddListener(
                delegate
                {
                    OnClickRoom(roomData.roomName);
                }
            );

            Debug.Log(roomData.roomName);
        }
    }

    void OnClickRoom(string roomName)
    {
        PhotonNetwork.NickName = "gg";
        PhotonNetwork.JoinRoom(roomName, null);
        PlayerPrefs.SetString("roomTitle", roomName);
        PlayerPrefs.SetString("USER_ID", PhotonNetwork.NickName);
    }

}
