using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GoPlayground : MonoBehaviour
{
    public GameObject mc;
    public Vector3 pos;

    // Start is called before the first frame update
    void Start()
    {
        Destroy(GetComponent<ClickExtinguisher>());
        mc = GameObject.Find("MainCamera");
    }

    // Update is called once per frame
    void Update()
    {
        GameObject.Find("Canvas").transform.Find("Cube").gameObject.SetActive(true);

        pos = mc.transform.position;

        if (20.5 <= pos.x && pos.x <= 30 && 3 < pos.z && pos.z < 10)
        {
            Debug.Log("도착 지점!");
            GameObject clikckedToggle = GameObject.Find("FifthToggle");
            Toggle t = clikckedToggle.GetComponent(typeof(Toggle)) as Toggle;
            t.isOn = true;

            GameObject.Find("Canvas").transform.Find("MissionClear").gameObject.SetActive(true);
        }

    }
}
