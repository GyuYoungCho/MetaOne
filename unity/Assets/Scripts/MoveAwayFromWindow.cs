using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MoveAwayFromWindow : MonoBehaviour
{
    public GameObject mc;
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {
        Destroy(GetComponent<ClickBackpack>());
        mc = GameObject.Find("MainCamera");
    }

    // Update is called once per frame
    void Update()
    {
        pos = mc.transform.position;

        GameObject clikckedToggle = GameObject.Find("ThirdToggle");
        Toggle t = clikckedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 범위 체크
        if (63 <= pos.x && pos.x <= 75 && 1 < pos.z && pos.z < 1.75)
        {
            Debug.Log("창문과 근접함");
            t.isOn = false;

            // 팝업창 추가
            GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
            GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
            Text pt = guideText.GetComponent(typeof(Text)) as Text;

            pt.text = "이동할 때는 창문과 떨어져서 이동해야 해!";

            // 3초 후 Guide 숨기기
            Invoke("hideGuide", 3);
        }

    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
