using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadScene : MonoBehaviour
{
//    private Text[] timeText = { "05", "00" };
    private float LimitTime = 300;
    public Text text_Timer;
    private bool startTimer = false;
    private int min, sec;

    void Update()
    {
        // 미션 클리어 패널이 null 아닐 때 타이머 멈추기
        if (GameObject.Find("MissionClear") != null) startTimer = false;

        // 타이머
        if (startTimer)
        {
            LimitTime -= Time.deltaTime;

            min = (int)LimitTime / 60;
            sec = ((int)LimitTime - min * 60) % 60;

            if (LimitTime < 60) text_Timer.color = Color.red;   // 시간이 1분 미만 남았을 경우

            if(min<= 0 && sec<=0)
            {
                text_Timer.text = "00 : 00";
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

        // 타이머 시작
        GameObject.Find("Canvas").transform.Find("Timer").gameObject.SetActive(true);
        startTimer = true;
    }

    public void clearMission()
    {
        // 결과 서버에 전송
//        아이디 & text_Timer.text

        // 메인 맵으로 이동
        SceneManager.LoadScene("Main");
    }
}
