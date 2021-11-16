using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CheckEmBell : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit hit = new RaycastHit();

            if (GetComponent<BoxCollider>().Raycast(ray, out hit, 10000f))
                clickEmergencyBell();
        }

    }

    public void clickEmergencyBell()
    {
        Debug.Log("비상벨 클릭했다!");
        GameObject clickedToggle = GameObject.Find("FirstToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // emergency 경보음 추가할 수 있으면 추가

        // 2번째 미션 추가
        foreach(GameObject obj in GameObject.FindGameObjectsWithTag("Napkin"))
        {
            obj.AddComponent<checkTowel>();
        }
    }
}
