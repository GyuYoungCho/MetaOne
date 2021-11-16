using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShakeEffect : MonoBehaviour
{
    public float power = 0.1f;
    public float duration = 1.0f;
    public Transform camera;
    public float slowDownAmount = 1.0f;

    Vector3 startPosition;
    float initialDuration;

    public bool shouldShake;
    public bool doOnce;

    // Start is called before the first frame update
    void Start()
    {
        camera = Camera.main.transform;
        startPosition = camera.localPosition;
        initialDuration = duration;
        shouldShake = true;
        doOnce = true;
    }

    // Update is called once per frame
    void Update()
    {

        if (GameObject.Find("BeforeStart") == null && doOnce)
        {
            addRigidbody();
        }

        if (GameObject.Find("BeforeStart") == null && shouldShake)
        {
            if (duration > 0)
            {
                camera.localPosition += Random.insideUnitSphere * power;
                duration -= Time.deltaTime * slowDownAmount;
            }
            else
            {
                shouldShake = false;
                duration = initialDuration;
                //camera.localPosition = startPosition;

                Invoke("shake", 10);
            }
        }
    }

    public void addRigidbody()
    {
        GameObject.Find("asset_kitchen_bin_072").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_blue_025").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_orange_057").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_pink_035").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_purple_031").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_turquese_048").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_backpack_turquese_049").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_blackboard_012").AddComponent<Rigidbody>();
        GameObject.Find("asset_int_clock_018").AddComponent<Rigidbody>();

        doOnce = false;
    }

    public void shake()
    {
        shouldShake = true;
    }
}
