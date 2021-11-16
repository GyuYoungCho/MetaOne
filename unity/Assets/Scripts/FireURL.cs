using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System.Runtime.InteropServices;

public class FireURL : MonoBehaviour
{
    [DllImport("__Internal")]
    private static extern void UnityEducationNameHook(string eduName);

    [DllImport("__Internal")]
    private static extern void UnityEducationAuthHook(string boolean);

    public GameObject btn;
    private bool isListened;
    private string characterName;

    public Renderer blackboard;

    public Material m1;
    public Material m2;

    // Start is called before the first frame update
    void Start()
    {
        isListened = false;
        btn.SetActive(false);
        characterName = PlayerPrefs.GetString("characterN");

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

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
            {
                // unity -> front로 교육명 전달
                UnityEducationNameHook("화재");

                // unity -> front로 교육 이수 유무 전달
                UnityEducationAuthHook("True");

                Application.OpenURL("https://schoolsafe.kr/post/view?id=630");
                isListened = true;
                btn.SetActive(true);
            }
        }

        if(isListened && checkArea())
        {
            btn.SetActive(true);
        }

        if (!checkArea())
        {
            btn.SetActive(false);
            blackboard.material = m1;
        }

        // 범위 체크하여 칠판 깜빡이도록
        if(checkArea())
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

        // 과학실 좌표 범위 -> x -19.31  -10.65  /  z -7.36  -19.72
        if (tr.position.x > -19.31 && tr.position.x < -10.65 && tr.position.z > -19.72 && tr.position.z < -7.36)
            return true;
        else return false;
    }
}
