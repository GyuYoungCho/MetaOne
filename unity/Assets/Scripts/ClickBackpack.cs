using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ClickBackpack : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        Destroy(GetComponent<GetUnderTheTable>());
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
                clickBackpack();
            }
        }
    }

    public void clickBackpack()
    {
        Debug.Log("책가방 클릭");
        GameObject clickedToggle = GameObject.Find("SecondToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 팝업창 추가
        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
        GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
        Text pt = guideText.GetComponent(typeof(Text)) as Text;

        pt.text = "이제 이 방을 탈출해서 복도로 나가자!";

        // 3초 후 Guide 숨기기
        Invoke("hideGuide", 3);

        // 3번째 미션 추가
        GameObject mission = GameObject.Find("MainCamera");
        mission.AddComponent<MoveAwayFromWindow>();

        // 4번째 미션 추가
        GameObject.Find("asset_int_extinguisher_017").AddComponent<ClickExtinguisher>();
    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }

}
