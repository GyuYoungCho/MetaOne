using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateCharacter : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        // prefab 동적 생성하여 캐릭터 생성
        // Ch_01 부분만 api 받아와서 수정!!
        GameObject character = (GameObject)Instantiate(Resources.Load("Low-poly characters pack/Prefabs/Ch_10"));
        character.name = "me";
        character.AddComponent<MovingCharacter>();
        character.transform.position = new Vector3(-0.7f, 0, -5.06f);
        character.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);

        // 하위에 카메라 추가
        GameObject mc = new GameObject("myCam");
        mc.transform.SetParent(character.transform);

        // 카메라 설정
        Camera camera = mc.AddComponent<Camera>();
        camera.transform.position = new Vector3(-3.34f, 3f, 5.94f);
        camera.transform.rotation = Quaternion.Euler(new Vector3(0.589f, -227.802f, -0.628f));

        // rigidbody 추가 및 설정
        Rigidbody rb = character.AddComponent<Rigidbody>();
        rb.constraints = RigidbodyConstraints.FreezeRotation;

        // capsule collider 추가 및 설정
        CapsuleCollider cc = character.AddComponent<CapsuleCollider>();
        cc.center = new Vector3(0, 2, 0);
        cc.height = 4;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
