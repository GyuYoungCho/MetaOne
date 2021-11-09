using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class firstPersonCam : MonoBehaviour
{
    public float turnSpeed = 2.0f; // 마우스 회전 속도    
    private float xRotate = 0.0f; // 내부 사용할 X축 회전량은 별도 정의 ( 카메라 위 아래 방향 )
    public float moveSpeed = 2.0f; // 이동 속도
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        // 좌우로 움직인 마우스의 이동량 * 속도에 따라 카메라가 좌우로 회전할 양 계산
        float yRotateSize = Input.GetAxis("Mouse X") * turnSpeed;
        // 현재 y축 회전값에 더한 새로운 회전각도 계산
        float yRotate = transform.eulerAngles.y + yRotateSize;

        // 위아래로 움직인 마우스의 이동량 * 속도에 따라 카메라가 회전할 양 계산(하늘, 바닥을 바라보는 동작)
        float xRotateSize = -Input.GetAxis("Mouse Y") * turnSpeed;
        // 위아래 회전량을 더해주지만 -45도 ~ 80도로 제한 (-45:하늘방향, 80:바닥방향)
        // Clamp 는 값의 범위를 제한하는 함수
        xRotate = Mathf.Clamp(xRotate + xRotateSize, -45, 80);

        // 카메라 회전량을 카메라에 반영(X, Y축만 회전)
        transform.eulerAngles = new Vector3(xRotate, yRotate, 0);

        //  키보드에 따른 이동량 측정
        Vector3 move = transform.forward * Input.GetAxis("Vertical") + transform.right * Input.GetAxis("Horizontal");

        // 이동량을 좌표에 반영
        transform.position += move * moveSpeed * Time.deltaTime;


        // 첫번째 미션
        pos = transform.position;
        if(73.5 <= pos.x && pos.x <= 74.2 && -4.2 < pos.z && pos.z < -3)
        {
            Debug.Log("범위 안에 들어왔다!");
            GameObject clikckedToggle = GameObject.Find("FirstToggle");
            Toggle t = clikckedToggle.GetComponent(typeof(Toggle)) as Toggle;
            t.isOn = true;

            // 팝업창 추가
            GameObject canvas = GameObject.Find("Canvas");

            GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
            GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
            Text pt = guideText.GetComponent(typeof(Text)) as Text;

            pt.text = "지진이 발생했다!\n교탁 아래로 들어가 몸을 보호해야해";

            // 3초 후 Guide 숨기기
            Invoke("hideGuide", 3);

            // 2번째 미션 추가
            GameObject.Find("asset_int_backpack_orange_057").AddComponent<ClickBackpack>();
        }

    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
