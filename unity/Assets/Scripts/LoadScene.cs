using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadScene : MonoBehaviour
{
    public void ChangeFire()
    {
        SceneManager.LoadScene("Fire");
    }

    public void quitMission()
    {
        SceneManager.LoadScene("Main");
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
        mission.AddComponent<MovingCharacter>();
    }

    public void clearMission()
    {
        // 결과 서버에 전송

        // 메인 맵으로 이동
        SceneManager.LoadScene("Main");
    }
}
