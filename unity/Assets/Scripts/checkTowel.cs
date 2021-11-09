using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEditor;

public class checkTowel : MonoBehaviour
{
    public const string BuiltinResources = "Resources/unity_builtin_extra";

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
                clickTowel();
        }
    }

    public void clickTowel()
    {
        Debug.Log("수건 클릭했다!");
        GameObject clickedToggle = GameObject.Find("SecondToggle");
        Toggle t = clickedToggle.GetComponent(typeof(Toggle)) as Toggle;
        t.isOn = true;

        // 가이드 대화 추가
        GameObject canvas = GameObject.Find("Canvas");

        GameObject.Find("Canvas").transform.Find("Guide").gameObject.SetActive(true);
        GameObject guideText = GameObject.Find("Guide").transform.Find("Text").gameObject;
        Text pt = guideText.GetComponent(typeof(Text)) as Text;

        /*GameObject panel = new GameObject("Guide");
        panel.transform.SetParent(canvas.transform);    // 캔버스의 하위로 추가

        RectTransform rt = panel.AddComponent<RectTransform>();
        rt.anchoredPosition = new Vector2(0, -374);
        rt.sizeDelta = new Vector2(1920f, 1080f);
        rt.pivot = new Vector2(0.5f, 0.5f);
        rt.transform.localScale = new Vector3(0.5f, 0.2f, 1f);

        Image pImg = panel.AddComponent<Image>();
        pImg.sprite = Resources.Load<Sprite>("bg") as Sprite;
        pImg.color = new Color32(29, 29, 29, 183);

        GameObject pText = new GameObject("Text");
        Text pt = pText.AddComponent<Text>();
        pText.transform.SetParent(panel.transform);*/

        pt.text = "수건을 찾았다!\n컵을 찾아서 물로 수건을 적시자!";
        /*pt.font = Resources.GetBuiltinResource(typeof(Font), "Arial.ttf") as Font;
        pt.alignment = TextAnchor.MiddleCenter;
        pt.fontSize = 50;
        pt.color = new Color32(255, 255, 255, 255);*/

        /*RectTransform ptrt = pText.GetComponent<RectTransform>();
        ptrt.anchoredPosition = new Vector2(0, 1);
        ptrt.sizeDelta = new Vector2(500f, 200f);*/

        // 3초 후 Guide 숨기기
        Invoke("hideGuide", 3);

        // 3번째 미션 추가
        GameObject.Find("asset_kitchen_glass_120").AddComponent<CheckCup>();

    }

    public void hideGuide()
    {
        if (GameObject.Find("Guide") != null)
            GameObject.Find("Guide").SetActive(false);
    }
}
