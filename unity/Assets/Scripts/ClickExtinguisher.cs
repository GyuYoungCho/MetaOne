using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ClickExtinguisher : MonoBehaviour
{
    void Start()
    {
        Destroy(GetComponent<MoveAwayFromWindow>());
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if (GetComponent<CapsuleCollider>().Raycast(ray, out hit, 10000f))
            {
                clickExtinguisher();
            }
        }
    }

    public void clickExtinguisher()
    {
        Debug.Log("소화기 클릭");
        GameObject clickedToggle = GameObject.Find("FourthToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 팝업창 추가
        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
        GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
        Text pt = guideText.GetComponent(typeof(Text)) as Text;

        pt.text = "화재를 대비해서 소화기 위치 파악하기!\n 이제 정문으로 가자!";

        // 3초 후 Guide 숨기기
        Invoke("hideGuide", 3);

        // 5번째 미션 추가
        GameObject mission = GameObject.Find("MainCamera");
        mission.AddComponent<GoPlayground>();
    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
