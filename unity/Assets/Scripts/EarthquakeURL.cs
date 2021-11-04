using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EarthquakeURL : MonoBehaviour
{   
    private GameObject target;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            target = GetClickedObject();
        }

        if (target.Equals(gameObject))
        {
            Application.OpenURL("https://schoolsafe.kr/post/view?id=1526");
            target = null;
        }
    }

    private GameObject GetClickedObject()
    {
        RaycastHit hit;
        GameObject target = null;

        // 마우스 포인트 근처 좌표를 만든다
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

        // 마우스 근처에 오브젝트가 있는지 확인
        if (Physics.Raycast(ray.origin, ray.direction*10, out hit))
        {
            // 있으면 오브젝트를 저장한다
            target = hit.collider.gameObject;            
        }

        return target;
    }
}
