using UnityEngine;
using UnityEditor;

[CustomEditor(typeof(DT_Tornado))]
public class DT_Custom : Editor
{
    SerializedProperty MaxDistance,
    RotationAxis,
    Lift,
    RotationStrength,
    TornadoStrength;

    void OnEnable(){
        MaxDistance = serializedObject.FindProperty("maxDistance");
        RotationAxis = serializedObject.FindProperty("rotationAxis");
        Lift = serializedObject.FindProperty("lift");
        RotationStrength = serializedObject.FindProperty("rotationStrength");
        TornadoStrength = serializedObject.FindProperty("tornadoStrength");
    }

    public override void OnInspectorGUI(){
        GUILayout.Box(Resources.Load("DT_Poster") as Texture, GUILayout.Width(400), GUILayout.Height(200));
        DT_Tornado script = (DT_Tornado)target;

        EditorGUILayout.HelpBox("If you like this asset please don't forget to leave a nice review. It helps alot.", MessageType.Info);

        EditorGUILayout.Space();

        //Max Distance
        EditorGUILayout.PropertyField(MaxDistance, new GUIContent("Max Distance", "Distance after which the rotation physics starts"));

        //Rotation Axis
        EditorGUILayout.PropertyField(RotationAxis, new GUIContent("Rotation Axis", "The axis that the caught objects will rotate around"));

        //Lift
        EditorGUILayout.PropertyField(Lift, new GUIContent("Lift", "Angle that is added to the object's velocity (higher lift -> quicker on top)"));

        //Rotation Strength
        EditorGUILayout.PropertyField(RotationStrength, new GUIContent("Rotation Strength", "The force that will drive the caught objects around the tornado's center"));

        //Tornado Strength
        EditorGUILayout.PropertyField(TornadoStrength, new GUIContent("Tornado Strength", "Tornado pull force"));
    
        serializedObject.ApplyModifiedProperties();
    }
}
