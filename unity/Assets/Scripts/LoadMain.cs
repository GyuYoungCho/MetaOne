using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

using System.Runtime.InteropServices;

public class LoadMain : MonoBehaviourPunCallbacks
{
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

    public GameObject Menu;
    public Slider slider;

    // Start is called before the first frame update
    void Start()
    {
        // 전달받은 데이터 불러오기
        characterName = PlayerPrefs.GetString("characterN");
        characterData = PlayerPrefs.GetString("character");
        roomTitle = PlayerPrefs.GetString("roomTitle");

        // 캐릭터 생성 (나)
        CreateCharacter();

        // photonNetwork의 데이터 통신을 다시 연결시켜준다. 
        PhotonNetwork.IsMessageQueueRunning = true;
        //PhotonNetwork.AutomaticallySyncScene = true;
        Invoke("CheckPlayerCount", 0.5f);

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

    // 캐릭터 생성
    void CreateCharacter()
    {
        Debug.Log(characterName);
        
        GameObject me = PhotonNetwork.Instantiate(characterName, new Vector3(-0.7f, 0, -5.06f), Quaternion.identity);
        
        CharacterController cc = me.AddComponent<CharacterController>();
        me.transform.position = new Vector3(28f, 0, -15f);
        me.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);
        cc.slopeLimit = 90f;
        cc.stepOffset = 0.1f;

        // 하위에 카메라 추가
        GameObject mc = new GameObject("Main Camera");
        mc.gameObject.tag = "MainCamera";
        mc.transform.SetParent(me.transform);

        // 카메라 설정
        mc.AddComponent<Camera>();
        mc.transform.position = new Vector3(28f, 6.4f, -8.4f);
        mc.transform.rotation = Quaternion.Euler(new Vector3(37.9f, -180f, -0.68f));

        // bgm listener
        mc.AddComponent<AudioListener>();

        // bgm 설정
        BGM = mc.AddComponent<AudioSource>();
        BGM.clip = Resources.Load("Sound/Rainbow Forest - Quincas Moreira") as AudioClip;
        BGM.loop = true;
        BGM.volume = 0.15f;
        BGM.Play();

        // 최초 설정
        PlayerPrefs.SetFloat("volume", BGM.volume);
        PlayerPrefs.SetInt("BgmState", 1);

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
    }

    // Menu panel on/off
    public void OnOffMenu()
    {
        if (GameObject.Find("MenuPanel") == null) Menu.SetActive(true);
        else Menu.SetActive(false);
    }

    // bgm on/off
    public void BGMOn()
    {
        BGM.Play();
        PlayerPrefs.SetInt("BgmState", 1);
    }

    public void BGMOff()
    {
        BGM.Pause();
        PlayerPrefs.SetInt("BgmState", 0);
    }

    // volume control
    public void VolumeControl()
    {
        BGM.volume = slider.value;
        PlayerPrefs.SetFloat("volume", slider.value);
    }
}
