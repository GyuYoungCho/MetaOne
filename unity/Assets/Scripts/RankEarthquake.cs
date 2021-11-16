using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using System.Runtime.InteropServices;

public class RankEarthquake : MonoBehaviour
{
    [DllImport("__Internal")]
    private static extern void UnityObjectHookk(string rank);

    private bool isListened;

    public Renderer ranking;

    public Material m1;
    public Material m2;

    // Start is called before the first frame update
    void Start()
    {
        isListened = false;

        // 랭킹 renderer 설정
        ranking.material = m1;
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
            {
                // unity -> front로 교육명 전달
                UnityObjectHookk("rank1");

                Application.OpenURL("https://k5a305.p.ssafy.io/rank");
                isListened = true;
            }
        }

        if (!checkArea())
        {
            ranking.material = m1;
        }

        // 범위 체크하여 랭킹 깜빡이도록
        if (checkArea())
        {
            float lerp = Mathf.PingPong(Time.time, 1.5f);
            ranking.material.Lerp(m1, m2, lerp);
        }
    }
    bool checkArea()
    {
        // 내 캐릭터의 위치 받아오기
        GameObject me = GameObject.FindWithTag("ME");
        if (me == null) return false;

        Transform tr = me.GetComponent<Transform>();

        // mini class 좌표 범위 -> x 63.22  75.33  /  z -6.86  2.22
        if (tr.position.x > 63.22 && tr.position.x < 75.33 && tr.position.z > -6.86 && tr.position.z < 2.22)
            return true;
        else return false;
    }
}
