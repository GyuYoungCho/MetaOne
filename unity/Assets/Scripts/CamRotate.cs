using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CamRotate : MonoBehaviour

{
    public float rotSpeed = 200f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // 사용자의 마우스 입력을 받아서 물체를 회전
        float mouse_X = Input.GetAxis("Mouse X");
        float mouse_Y = Input.GetAxis("Mouse Y");

        // 마우스 입력 값을 이용해 회전 방향을 결정
        Vector3 dir = new Vector3(-mouse_Y, mouse_X, 0);

        // 회전 방향으로 물체를 회전
        transform.eulerAngles += dir * rotSpeed * Time.deltaTime;

        // x축 회전 값을 -90도에서 90도 사이로 제한
        Vector3 rot = transform.eulerAngles;
        rot.x = Mathf.Clamp(rot.x, -90f, 90f);
        transform.eulerAngles = rot;       

    }
}
