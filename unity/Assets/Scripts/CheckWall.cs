using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CheckWall : MonoBehaviour
{
    public GameObject mc;
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {
        // 캐릭터(= 메인카메라)
        mc = GameObject.Find("MainCamera");
    }

    // Update is called once per frame
    void Update()
    {
        pos = mc.transform.position;

        if (pos.z > 0.64f)
        {
            if (pos.x < -30f) checkWall();
            else warning();
        }
        if (pos.x > -30f)
        {
            if (pos.z < 0.64f) checkWall();
            else warning();
        }

    }

    // 벽에 붙어서 이동하는지
    public void checkWall()
    {
        Debug.Log("벽에 붙어있나 확인!");
        GameObject clickedToggle = GameObject.Find("FourthToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(false);

        // 4번째 미션 시작
        GameObject mission = GameObject.Find("MainCamera");
        if(mission.GetComponent<CheckOut>() == null) mission.AddComponent<CheckOut>();
    }

    public void warning()
    {
        // 가이드 대화 추가
        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
        GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
        Text pt = guideText.GetComponent(typeof(Text)) as Text;

        pt.text = "벽에 붙어서 이동해야해!";

        // 4번째 미션 취소
        if (GameObject.Find("MainCamera").GetComponent<CheckOut>() != null)
            Destroy(GetComponent<CheckOut>());
    }
}
