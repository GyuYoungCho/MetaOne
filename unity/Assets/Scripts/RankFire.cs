using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using System.Runtime.InteropServices;

public class RankFire : MonoBehaviour
{
    [DllImport("__Internal")]
    private static extern void UnityObjectHook(string obj);

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
                UnityObjectHook("rank2");
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
        if (tr.position.x > -19.31 && tr.position.x < -10.65 && tr.position.z > -19.72 && tr.position.z < -7.36)
            return true;
        else return false;
    }
}
