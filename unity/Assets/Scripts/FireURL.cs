using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireURL : MonoBehaviour
{
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

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
            {
                Application.OpenURL("https://schoolsafe.kr/post/view?id=630");
                btn.SetActive(true);
            }
        }
    }
}
