using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GetUnderTheTable : MonoBehaviour
{
    public GameObject mc;
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {
        mc = GameObject.Find("MainCamera");
    }

    // Update is called once per frame
    void Update()
    {
        pos = mc.transform.position;

        if (73.5 <= pos.x && pos.x <= 74.2 && -4.2 < pos.z && pos.z < -3)
        {
            Debug.Log("범위 안에 들어왔다!");
            GameObject clikckedToggle = GameObject.Find("FirstToggle");
            Toggle t = clikckedToggle.GetComponent(typeof(Toggle)) as Toggle;
            t.isOn = true;

            // 팝업창 추가
            GameObject canvas = GameObject.Find("Canvas");

            GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
            GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
            Text pt = guideText.GetComponent(typeof(Text)) as Text;

            pt.text = "지진이 발생했다!\n교탁 아래로 들어가 몸을 보호해야해";

            // 3초 후 Guide 숨기기
            Invoke("hideGuide", 3);

            // 2번째 미션 추가
            GameObject.Find("asset_int_backpack_orange_057").AddComponent<ClickBackpack>();
        }

    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
