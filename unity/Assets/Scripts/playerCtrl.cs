using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using Photon.Pun;
using Photon.Realtime;
//using UnityStandardAssets.Utility;

public class playerCtrl : MonoBehaviourPunCallbacks, IPunObservable
{
    private float h, v, r;
    private Transform tr;
    private Animator anim;
    // private Rigidbody rigidBody;
    public float moveSpeed = 20.0f;
    public float rotSpeed = 30.0f;
    CharacterController cc;
    Vector3 playerPosition;
    float gravity = 50.0f;

    // Start is called before the first frame update
    void Start()
    {
        tr = GameObject.Find("Character").GetComponent<Transform>();
        anim = GameObject.Find("Character").GetComponent<Animator>();
        //rigidBody = GetComponent<Rigidbody>();
        cc = GetComponent<CharacterController>();
        
    }

    // Update is called once per frame
    void Update()
    {
        if (photonView.IsMine)
        {
            h = Input.GetAxis("Horizontal");    // 좌우값
            v = Input.GetAxis("Vertical");      // 상하값

            // 이동 방향 설정
            Vector3 dir = new Vector3(h, 0, v);
            dir = dir.normalized;

            // 메인 카메라를 기준으로 방향을 변환
            //dir = Camera.main.transform.TransformDirection(dir);
            //transform.position += dir * moveSpeed * Time.deltaTime;

            //cc.Move(dir * moveSpeed * Time.deltaTime);

            // 각도 조절
            Debug.Log("dir >> " + dir + " vector3  >>  " + Vector3.zero);
            if (dir != Vector3.zero)
            {
                float angle = Mathf.Atan2(dir.x, dir.z) * Mathf.Rad2Deg;
                if (Mathf.Abs(angle) == 90) angle *= -1f;
                else angle += 180f; 

                // 즉시 회전
                //tr.rotation = Quaternion.Euler(0, angle, 0);

                // 부드러운 회전
                tr.rotation = Quaternion.Slerp(tr.rotation, Quaternion.Euler(0, angle, 0), rotSpeed * Time.fixedDeltaTime);

            }

            // character controller에는 중력 자동으로 붙어있지 X
            // 따로 코드로 적용시켜줘야함
            dir.y -= gravity * Time.deltaTime;

            //dir = dir * moveSpeed * Time.deltaTime;
            //cc.Move(dir * moveSpeed * Time.deltaTime);

            //tr.Translate(Vector3.forward * v * moveSpeed * Time.deltaTime);
            //tr.Rotate(Vector3.up * h * rotSpeed * Time.deltaTime);
            Debug.Log("h >> " + h + " , v >>  " + v);
            //Anim(h, v);

            // 움직임
            Movement();

            // 중력 적용
            h = Input.GetAxis("Horizontal");    // 좌우값
            v = Input.GetAxis("Vertical");      // 상하값
            dir = new Vector3(h, 0, v);
            dir = dir.normalized;
            dir.y -= gravity * Time.deltaTime;
            dir = dir * moveSpeed * Time.deltaTime;
            cc.Move(dir * moveSpeed * Time.deltaTime);
        }
        else
        {
            tr.position = Vector3.Lerp(tr.position, currPos, Time.deltaTime * 7f);
            tr.rotation = Quaternion.Slerp(tr.rotation, currRot, Time.deltaTime * 7f);
        }
    }

    private Vector3 currPos;
    private Quaternion currRot;

    public void OnPhotonSerializeView(PhotonStream stream, PhotonMessageInfo info)
    {
        if (stream.IsWriting)
        {
            // Sending Datas ...
            stream.SendNext(tr.position);
            stream.SendNext(tr.rotation);
        }
        else
        {
            currPos = (Vector3)stream.ReceiveNext();
            currRot = (Quaternion)stream.ReceiveNext();
        }

    }

    public void Movement()
    {
        playerPosition = this.transform.position;

        if(Input.GetKey(KeyCode.W))
        {
            cc.Move(Vector3.back * moveSpeed * Time.deltaTime);
            anim.SetBool("IsWalking", true);
        }
        if (Input.GetKey(KeyCode.A))
        {
            cc.Move(Vector3.right * moveSpeed * Time.deltaTime);
            anim.SetBool("IsWalking", true);
        }
        if (Input.GetKey(KeyCode.S))
        {
            cc.Move(Vector3.forward * moveSpeed * Time.deltaTime);
            anim.SetBool("IsWalking", true);
        }
        if (Input.GetKey(KeyCode.D))
        {
            //playerPosition -= Vector3.left * moveSpeed * Time.deltaTime;
            //rigidBody.MovePosition(playerPosition);
            cc.Move(Vector3.left * moveSpeed * Time.deltaTime);
            anim.SetBool("IsWalking", true);
        }

        if(!Input.GetKey(KeyCode.W) && !Input.GetKey(KeyCode.S) && !Input.GetKey(KeyCode.D) && !Input.GetKey(KeyCode.A))
            anim.SetBool("IsWalking", false);
    }

    private void Anim(float h, float v)
    {
        bool isWalking = (h != 0 || v != 0);
        Debug.Log("isWalking >>> " + isWalking);
        anim.SetBool("IsWalking", isWalking);
    }
}
