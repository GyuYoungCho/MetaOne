using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using System.Runtime.InteropServices;

public class EarthquakeURL : MonoBehaviour
{
    [DllImport("__Internal")]
    private static extern void UnityEducationNameHook(string eduName);

    [DllImport("__Internal")]
    private static extern void UnityEducationAuthHook(bool auth);

    public GameObject btn;
    private bool isListened;
    private int isEducated;

    public Renderer blackboard;

    public Material m1;
    public Material m2;

    // Start is called before the first frame update
    void Start()
    {
        isListened = false;
        btn.SetActive(false);
        isEducated = PlayerPrefs.GetInt("earthquake");

        // 칠판 renderer 설정
        blackboard.material = m1;
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if(GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
            {
                // unity -> front로 교육명 전달
                UnityEducationNameHook("earthquake");

                Application.OpenURL("https://schoolsafe.kr/post/view?id=1526");

                // 교육 이수한적 없다면
                if (isEducated == 0)
                {
                    isEducated = 1;
                    PlayerPrefs.SetInt("fire", 1);

                    // unity -> front로 교육 이수 유무 전달
                    UnityEducationAuthHook(true);
                }
                
                btn.SetActive(true);
            }
        }

        if (isEducated == 1 && checkArea())
        {
            btn.SetActive(true);
        }

        if (!checkArea())
        {
            btn.SetActive(false);
            blackboard.material = m1;
        }

        // 범위 체크하여 칠판 깜빡이도록
        if (checkArea())
        {
            float lerp = Mathf.PingPong(Time.time, 1.5f);
            blackboard.material.Lerp(m1, m2, lerp);
        }
    }

    // 교실 안에 위치해있는지 체크
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
