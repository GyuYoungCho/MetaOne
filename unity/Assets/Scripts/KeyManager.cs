using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KeyManager : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        DontDestroyOnLoad(this.gameObject);
    }

    public void FocusCanvas(string focus)
    {
#if !UNITY_EDITOR && UNITY_WEBGL
    if (focus == "0")
    {
        WebGLInput.captureAllKeyboardInput = false;
    }
    // disable WebGLInput.captureAllKeyboardInput so elements in web page can handle keabord inputs
    else
    {
        WebGLInput.captureAllKeyboardInput = true;
    }
#endif
    }
}
