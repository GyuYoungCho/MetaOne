using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

using Photon.Pun;   // 유니티용 포톤 컴포넌트
using Photon.Realtime;  // 포톤 서비스 관련 라이브러리

public class ChooseChar : MonoBehaviour
{
    public Button preBtn, nextBtn;
    public Text characterNum;
    public GameObject character;
    private GameObject switchCharacter;
    public int charNum=1;

    // 전달할 데이터
    string characterData;
    string characterName;

    // Start is called before the first frame update
    void Start()
    {
        // Default 캐릭터 설정
        characterData = "Low-poly characters pack/Prefabs/Ch_01";
        characterName = "Ch_01";
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    // 이전 캐릭터
    public void getPreCharacter()
    {
        charNum -= 1;
        if (charNum == 0) charNum = 20;

        setCharacter();
    }

    // 다음 캐릭터
    public void getNextCharacter()
    {
        charNum += 1;
        if (charNum == 21) charNum = 1;

        setCharacter();
    }

    // prefab 설정
    public void setCharacter()
    {
        // 이전 캐릭터 삭제
        Transform[] child = character.GetComponentsInChildren<Transform>(true);

        foreach(var iter in child)
        {
            if (iter != character.transform) Destroy(iter.gameObject);
        }

        // 새로운 캐릭터 추가
        if (charNum < 10)
        {
            characterData = "Low-poly characters pack/Prefabs/Ch_0" + charNum;
            characterName = "Ch_0" + charNum;
            switchCharacter = (GameObject)Instantiate(Resources.Load(characterData));
        }
        else
        {
            characterData = "Low-poly characters pack/Prefabs/Ch_" + charNum;
            characterName = "Ch_" + charNum;
            switchCharacter = (GameObject)Instantiate(Resources.Load(characterData));
        }

        switchCharacter.transform.position = new Vector3(-25.1272f, -0.01000977f, 2.327881f);
        switchCharacter.transform.rotation = Quaternion.Euler(new Vector3(0f, -79.885f, 0f));
        switchCharacter.transform.localScale = new Vector3(0.3f, 0.3f, 0.3f);

        switchCharacter.transform.SetParent(character.transform);

        characterNum.text = charNum + "/20";
    }

    // 캐릭터 선택 완료
    public void selectCharacter()
    {
        // 캐릭터 데이터 저장
        PlayerPrefs.SetString("character", characterData);
        PlayerPrefs.SetString("characterN", characterName);

        // 다음 씬(방 선택) 로드
        PhotonNetwork.LoadLevel("ChooseRoom");
    }
}
