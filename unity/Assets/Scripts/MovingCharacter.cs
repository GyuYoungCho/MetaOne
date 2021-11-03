using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MovingCharacter : MonoBehaviour
{
    public GameObject btn1;
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        float moveZ = 0f;
        float moveX = 0f;

        if (Input.GetKey(KeyCode.W))
        {
            moveZ += 1f;
        }

        if (Input.GetKey(KeyCode.S))
        {
            moveZ -= 1f;
        }

        if (Input.GetKey(KeyCode.A))
        {
            moveX -= 1f;
        }

        if (Input.GetKey(KeyCode.D))
        {
            moveX += 1f;
        }

        transform.Translate(new Vector3(moveX, 0f, moveZ) * 0.1f);

        pos = transform.position;

        if (pos.x >= -18.96 && pos.x <= -10.85 && pos.z <= -7.24 && pos.z >= -19.65)
        {
            btn1.SetActive(true);
            Debug.Log("보여");

            // 영상봤는지 체크
            // 영상 봤으면 버튼 활성화
        }
        else
        {
            btn1.SetActive(false);
            Debug.Log("안보여");
        }
    }
}
