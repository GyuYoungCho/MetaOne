using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;
using UnityEngine.SceneManagement;
using System.Threading;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

using System;   // convert

using System.Runtime.InteropServices;

public class CreateRoom : MonoBehaviourPunCallbacks
{
    [DllImport("__Internal")]
    private static extern void UnityRoomHook(string roomName);

    string characterData, characterName;
    int IsOut;
    byte limit = 4; // 방 인원제한
    RoomInfo[] roomInfoList;

    public GameObject room;
    public Transform gridTr;
    public TMP_InputField txtRoomName;

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
        IsOut = PlayerPrefs.GetInt("IsOut");    // 방 나가기로 씬 이동한건지?

        if (PhotonNetwork.IsConnected)
        {
            Debug.Log("still connected!!");
            PhotonNetwork.JoinLobby();
        }
    }

    // Update is called once per frame
    void Update()
    {

    }

    // 방만들기 버튼 click
    public void createNewRoom()
    {
        // 방 선택 패널 비활성화
        GameObject.Find("Panel").SetActive(false);

        // 방 생성 패널 활성화
        GameObject.Find("Canvas").transform.Find("MakeRoom").gameObject.SetActive(true);
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

        // unity -> front로 방 이름 전달(방 생성 시)
        UnityRoomHook(PhotonNetwork.CurrentRoom.Name);

        SceneManager.LoadScene("Main");
    }

    // 취소하기 버튼 click
    public void cancelCreateRoom()
    {
        // 방 생성 패널 비활성화
        GameObject.Find("MakeRoom").SetActive(false);

        // 방 선택 패널 활성화
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);
    }

    // 생성하기 버튼 click
    public void onClickCreateRoom()
    {
        // 방 생성 패널 비활성화
        GameObject.Find("MakeRoom").SetActive(false);

        // 방 선택 패널 활성화
        GameObject.Find("Canvas").transform.Find("Panel").gameObject.SetActive(true);

        // 방 생성
        PhotonNetwork.CreateRoom(txtRoomName.text, new RoomOptions { MaxPlayers = 4, PlayerTtl = 60000 });
    }

    // 방 정보
    // 방 정보가 변경되었을 때만 호출됨
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
            {   // null 이여서 오류나면 ChooseRoom Scene 한번 더 로드
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
        // unity -> front로 방 이름 전달(이미 생성되어 있는 방 클릭 시)
        UnityRoomHook(roomName);
        PhotonNetwork.JoinRoom(roomName, null);
        PlayerPrefs.SetString("roomTitle", roomName);
        PlayerPrefs.SetString("USER_ID", PhotonNetwork.NickName);
    }

    // front -> unity 유저 닉네임 가져오기
    void initPlayerNickName(string nickname)
    {
        PhotonNetwork.NickName = nickname;
    }

    // front -> unity 화재 교육 이수 여부
    void isFireEducated(int state)
    {
        PlayerPrefs.SetInt("fire", state);
        Debug.Log("isFireEducated >> " + state);
    }

    // front -> unity 지진 교육 이수 여부
    void isEarthquakeEducated(int state)
    {
        PlayerPrefs.SetInt("earthquake", state);
        Debug.Log("isEarthquakeEducated >> " + state);
    }

}
