using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

public class LoadMain : MonoBehaviourPunCallbacks
{
    //[DllImport("__Internal")]
    //private static extern void UnityCharacterHook(string str);

    string characterName;
    string characterData;
    string roomTitle;

    GameObject focus;
    Transform tr;

    private AudioSource BGM;
    private AudioClip bgmSource;

    public Renderer rend1;
    public Renderer rend2;
    public Renderer rend3;

    public Material m1;
    public Material m2;

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
        //PhotonNetwork.AutomaticallySyncScene = true;
        Invoke("CheckPlayerCount", 0.5f);

        Debug.Log(characterData);

        // 캐릭터의 transform
        //tr = focus.transform;

        // 방명록 책들 renderer 설정
        rend1.material = m1;
        rend2.material = m1;
        rend3.material = m1;

    }

    // Update is called once per frame
    void Update()
    {
        // 범위 체크하여 해당 범위 안에 있으면 책 깜빡이도록
        if(focus != null && tr.position.x > 7.358266 && tr.position.x < 27.2808)
        {
            float lerp = Mathf.PingPong(Time.time, 1.5f);
            rend1.material.Lerp(m1, m2, lerp);
            rend2.material.Lerp(m1, m2, lerp);
            rend3.material.Lerp(m1, m2, lerp);
        }
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
        mc.transform.position = new Vector3(28f, 6.4f, -8.4f);
        mc.transform.rotation = Quaternion.Euler(new Vector3(37.9f, -180f, -0.68f));
        //mc.transform.position = new Vector3(28f, 3.62f, -10.12f);
        //mc.transform.rotation = Quaternion.Euler(new Vector3(26.251f, 180f, -0.6f));

        // 
        mc.AddComponent<AudioListener>();

        // bgm 설정
        BGM = mc.AddComponent<AudioSource>();
        BGM.clip = Resources.Load("Sound/Rainbow Forest - Quincas Moreira") as AudioClip;
        BGM.loop = true;
        BGM.volume = 0.15f;
        BGM.Play();

        // rigidbody 추가 및 설정
        //Rigidbody rb = me.AddComponent<Rigidbody>();
        //rb.constraints = RigidbodyConstraints.FreezeRotation;

        // character controller 추가 및 설정
        cc.center = new Vector3(0, 2.1f, 0);
        cc.height = 4;

        // 내 캐릭터 찾아야할 때를 위해 ME 태그 추가
        me.tag = "ME";

        focus = GameObject.FindWithTag("ME");
        tr = focus.transform;
    }

    void CheckPlayerCount()
    {
        int currPlayer = PhotonNetwork.PlayerList.Length;
        int maxPlayer = PhotonNetwork.CurrentRoom.MaxPlayers;
        //        playerCount.text = string.Format("[{0}/{1}]", currPlayer, maxPlayer);

        Debug.Log("현재 몇명? " + currPlayer);
    }

}
