using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

public class LoadMain : MonoBehaviourPunCallbacks
{
    string characterName;
    string characterData;

    // Start is called before the first frame update
    void Start()
    {
        // 전달받은 데이터 불러오기
        characterName = PlayerPrefs.GetString("characterN");
        characterData = PlayerPrefs.GetString("character");

        CreateCharacter();
        // photonNetwork의 데이터 통신을 다시 연결시켜준다. 
        PhotonNetwork.IsMessageQueueRunning = true;
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

        me.AddComponent<MovingCharacter>();
        me.transform.position = new Vector3(-0.7f, 0, -5.06f);
        me.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);

        // 하위에 카메라 추가
        GameObject mc = new GameObject("myCam");
        mc.transform.SetParent(me.transform);

        // 카메라 설정
        Camera camera = mc.AddComponent<Camera>();
        camera.transform.position = new Vector3(-3.34f, 3f, 5.94f);
        camera.transform.rotation = Quaternion.Euler(new Vector3(0.589f, -227.802f, -0.628f));

        // rigidbody 추가 및 설정
        Rigidbody rb = me.AddComponent<Rigidbody>();
        rb.constraints = RigidbodyConstraints.FreezeRotation;

        // capsule collider 추가 및 설정
        CapsuleCollider cc = me.AddComponent<CapsuleCollider>();
        cc.center = new Vector3(0, 2, 0);
        cc.height = 4;

    }

    void CheckPlayerCount()
    {
        int currPlayer = PhotonNetwork.PlayerList.Length;
        int maxPlayer = PhotonNetwork.CurrentRoom.MaxPlayers;
//        playerCount.text = string.Format("[{0}/{1}]", currPlayer, maxPlayer);
    }
}
