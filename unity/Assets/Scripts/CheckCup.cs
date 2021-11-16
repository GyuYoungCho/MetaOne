using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CheckCup : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        Destroy(GetComponent<checkTowel>());
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
                clickCup();
        }
    }

    public void clickCup()
    {
        Debug.Log("컵 클릭했다!");
        GameObject clickedToggle = GameObject.Find("ThirdToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 가이드 대화 추가
        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
        GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
        Text pt = guideText.GetComponent(typeof(Text)) as Text;

        pt.text = "수건을 적셨다!\n숨쉬기가 한결 편해졌어.";

        // 3초 후 Guide 숨기기
        Invoke("hideGuide", 3);

        // 4번째 미션 추가
        GameObject mission = GameObject.Find("MainCamera");
        mission.AddComponent<CheckWall>();

        // 3번째 미션 삭제
        foreach (GameObject obj in GameObject.FindGameObjectsWithTag("Cup"))
        {
            Destroy(obj.GetComponent<CheckCup>());
        }
    }

    public void hideGuide()
    {
        if(GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
