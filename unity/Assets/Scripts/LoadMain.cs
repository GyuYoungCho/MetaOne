using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

public class LoadMain : MonoBehaviourPunCallbacks
{
    string characterName;
    string characterData;
    string roomTitle;
   
    // Start is called before the first frame update
    void Start()
    {
        // 전달받은 데이터 불러오기
        characterName = PlayerPrefs.GetString("characterN");
        characterData = PlayerPrefs.GetString("character");
        roomTitle = PlayerPrefs.GetString("roomTitle");

        CreateCharacter();

        // photonNetwork의 데이터 통신을 다시 연결시켜준다. 
        PhotonNetwork.IsMessageQueueRunning = true;
        PhotonNetwork.AutomaticallySyncScene = true;
        Invoke("CheckPlayerCount", 0.5f);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void CreateCharacter()
    {
        Debug.Log(characterName);
        
        GameObject me = PhotonNetwork.Instantiate(characterName, new Vector3(-0.7f, 0, -5.06f), Quaternion.identity);

        //me.AddComponent<MovingCharacter>();
        CharacterController cc = me.AddComponent<CharacterController>();
        me.transform.position = new Vector3(28f, 0, -15f);
        me.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);

        // 하위에 카메라 추가
        GameObject mc = new GameObject("Main Camera");
        mc.gameObject.tag = "MainCamera";
        mc.transform.SetParent(me.transform);

        // 카메라 설정
        mc.AddComponent<Camera>();
        mc.transform.position = new Vector3(28f, 3.62f, -10.12f);
        mc.transform.rotation = Quaternion.Euler(new Vector3(26.251f, 180f, -0.6f));

        // rigidbody 추가 및 설정
        //Rigidbody rb = me.AddComponent<Rigidbody>();
        //rb.constraints = RigidbodyConstraints.FreezeRotation;

        // character controller 추가 및 설정
        cc.center = new Vector3(0, 2.1f, 0);
        cc.height = 4;

        // 내 캐릭터 찾아야할 때를 위해 ME 태그 추가
        me.tag = "ME";
    }

    void CheckPlayerCount()
    {
        int currPlayer = PhotonNetwork.PlayerList.Length;
        int maxPlayer = PhotonNetwork.CurrentRoom.MaxPlayers;
        //        playerCount.text = string.Format("[{0}/{1}]", currPlayer, maxPlayer);

        Debug.Log("현재 몇명? " + currPlayer);
    }
}
