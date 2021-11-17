using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BillBoard : MonoBehaviour
{
    private void LateUpdate()
    {
        Vector3 tr = Camera.main.transform.position;

        transform.LookAt(tr);
    }
}