using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CheckOut : MonoBehaviour
{
    public GameObject mc, cafeteria;
    public Vector3 pos, cPos;

    // Start is called before the first frame update
    void Start()
    {
        // 캐릭터(= 메인카메라)
        mc = GameObject.Find("MainCamera");

        // cafeteria 범위
        cafeteria = GameObject.Find("Cafeteria");
        cPos = cafeteria.transform.position;
    }

    // Update is called once per frame
    void Update()
    {
        pos = mc.transform.position;

        // 식당 안 범위 체크
        if (pos.x < -3.63 && pos.x > -32.3 && pos.z > -0.18 && pos.z < 31.57)
        {        
        }
        else checkOut();

    }

    // 밖으로 대피했는지
    public void checkOut()
    {
        Debug.Log("밖으로 대피 완료!");
        GameObject clickedToggle = GameObject.Find("FifthToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 미션완료(성공)
        GameObject.Find("Canvas").transform.Find("MissionClear").gameObject.SetActive(true);

        Destroy(mc.GetComponent<firstPersonCam>());
        Destroy(mc.GetComponent<CheckWall>());
        Destroy(mc.GetComponent<CheckOut>());
    }
}
