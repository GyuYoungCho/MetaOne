using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CheckEmBell : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // 클릭 감지
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
                clickEmergencyBell();
        }

    }

    public void clickEmergencyBell()
    {
        Debug.Log("비상벨 클릭했다!");
        GameObject clickedToggle = GameObject.Find("FirstToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // emergency 경보음 추가 (음소거 아닐경우 로비에서 지정한 볼륨에 맞게 play)
        if(PlayerPrefs.GetInt("BgmState") == 1)
        {
            GameObject mc = GameObject.Find("MainCamera");
            AudioSource emergencyBell = mc.AddComponent<AudioSource>();
            emergencyBell.clip = Resources.Load("Sound/Emergency Siren Close Long") as AudioClip;
            emergencyBell.loop = true;
            emergencyBell.volume = PlayerPrefs.GetFloat("volume") + 0.2f;
            emergencyBell.Play();
        }

        // 2번째 미션 추가
        foreach (GameObject obj in GameObject.FindGameObjectsWithTag("Napkin"))
        {
            obj.AddComponent<checkTowel>();
        }
    }
}
