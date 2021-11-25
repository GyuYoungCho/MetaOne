using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

using UnityEngine.SceneManagement;

// 마스터(매치 메이킹) 서버와 룸 접속
public class LobbyManager : MonoBehaviourPunCallbacks
{
    private string gameVersion = "1";   // 게임 버전

    public Text connectionInfoText; // 네트워크 정보를 표시할 텍스트
    public Button joinButton;   // 룸 접속 버튼

    // 게임 실행과 동시에 마스터 서버 접속 시도
    // Start is called before the first frame update
    void Start()
    {
        // 접속에 필요한 정보(게임 버전) 설정
        PhotonNetwork.GameVersion = gameVersion;

        // 설정한 정보를 가지고 마스터 서버 접속 시도 (APP_ver, Region...)
        PhotonNetwork.ConnectUsingSettings();

        // 룸 접속 버튼 잠시 비활성화;
        joinButton.interactable = false;

        // 접속 시도 중임을 텍스트로 표시
        connectionInfoText.text = "마스터 서버에 접속중 ...";
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    // 마스터 서버 접속 성공시 자동 실행
    public override void OnConnectedToMaster()
    {
        // 룸 접속 버튼 활성화
        joinButton.interactable = true;

        // 접속 정보 표시
        connectionInfoText.text = "온라인 : 마스터 서버와 연결됨";

        PhotonNetwork.JoinLobby();
    }

    // 마스터 서버 접속 실패시 자동 실행
    public override void OnDisconnected(DisconnectCause cause)
    {
        // 룸 접속 버튼 비활성화
        joinButton.interactable = false;

        // 접속 정보 표시
        connectionInfoText.text = "오프라인 : 마스터 서버와 연결되지 않음\n접속 재시도 중 ...";

        // 마스터 서버 재접속 시도
        PhotonNetwork.ConnectUsingSettings();
    }

    // 캐릭터 선택으로 Scene 이동
    public void goChooseCharacter()
    {
        Debug.Log(PhotonNetwork.AppVersion);
        PhotonNetwork.LoadLevel("ChooseCharacter");
    }

}
