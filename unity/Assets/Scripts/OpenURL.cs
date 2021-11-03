using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class OpenURL : MonoBehaviour
{

    public Button btn1;

    void Start()
    {
        Debug.Log("붙였음?");
        btn1.onClick.AddListener(startVideo);
        Debug.Log("ㅇㅇ붙였음");
    }

    public void startVideo()
    {
        Debug.Log("클릭됨?");
        Application.OpenURL("https://www.youtube.com/watch?v=09hAnqzR7Qc");
        Debug.Log("clicked!");
    }
}
