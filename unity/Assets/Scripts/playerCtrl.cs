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
    public float moveSpeed = 7f;
    public float rotSpeed = 60.0f;
    CharacterController cc;

    // Start is called before the first frame update
    void Start()
    {
        tr = GetComponent<Transform>();
        anim = GetComponent<Animator>();
        cc = GetComponent<CharacterController>();

        if (photonView.IsMine)
        {
 //           Camera.main.GetComponent<Transform>() = tr;
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (photonView.IsMine)
        {
            h = Input.GetAxis("Horizontal");
            v = Input.GetAxis("Vertical");

            // 이동 방향 설정
            Vector3 dir = new Vector3(h, 0, v);
            dir = dir.normalized;

            // 메인 카메라를 기준으로 방향을 변환
            dir = Camera.main.transform.TransformDirection(dir);

            transform.position += dir * moveSpeed * Time.deltaTime;

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
}
