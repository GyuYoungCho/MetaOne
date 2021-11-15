using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

using System.Runtime.InteropServices;

public class EarthquakeURL : MonoBehaviour
{
    [DllImport("__Internal")]
    private static extern void UnityEducationNameHook(string eduName);

    [DllImport("__Internal")]
    private static extern void UnityEducationAuthHook(string boolean);

    public GameObject btn;

    // Start is called before the first frame update
    void Start()
    {
        btn.SetActive(false);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if(GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
            {
                // unity -> front로 교육명 전달
                UnityEducationNameHook("지진");

                // unity -> front로 교육 이수 유무 전달
                UnityEducationAuthHook("True");

                Application.OpenURL("https://schoolsafe.kr/post/view?id=1526");
                btn.SetActive(true);
            }
        }
    }

}
