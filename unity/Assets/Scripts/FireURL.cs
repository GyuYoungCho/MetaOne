using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireURL : MonoBehaviour
{
    public GameObject btn;
    private bool isListened;
    private string characterName;

    // Start is called before the first frame update
    void Start()
    {
        isListened = false;
        btn.SetActive(false);
        characterName = PlayerPrefs.GetString("characterN");
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
                Application.OpenURL("https://schoolsafe.kr/post/view?id=630");
                isListened = true;
                btn.SetActive(true);
            }
        }

        if(isListened && checkArea())
        {
            btn.SetActive(true);
        }

        if (!checkArea()) btn.SetActive(false);
    }

    // 교실 안에 위치해있는지 체크
    bool checkArea()
    {
        // 내 캐릭터의 위치 받아오기
        GameObject me = GameObject.FindWithTag("ME");
        Transform tr = me.GetComponent<Transform>();

        // 과학실 좌표 범위 -> x -19.31  -10.65  /  z -7.36  -19.72
        if (tr.position.x > -19.31 && tr.position.x < -10.65 && tr.position.z > -19.72 && tr.position.z < -7.36)
            return true;
        else return false;
    }
}
