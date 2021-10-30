// Import the functions you need from the SDKs you need
// importScripts("https://www.gstatic.com/firebasejs/9.1.3/firebase-app.js");
// importScripts("https://www.gstatic.com/firebasejs/9.1.3/firebase-messaging.js");
// importScripts("https://www.gstatic.com/firebasejs/9.1.3/firebase-analytics.js");
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const API_BASE_URL = "https://i5a305.p.ssafy.io:8000";
const FIREBASE_CONFIG = {
  apiKey: "AIzaSyCKsCv78jJlewnvHKNk30YzevYaaOY74fI",
  authDomain: "favorable-bolt-113915.firebaseapp.com",
  projectId: "favorable-bolt-113915",
  storageBucket: "favorable-bolt-113915.appspot.com",
  messagingSenderId: "906874790684",
  appId: "1:906874790684:web:a19307cd8665c262bf50d9",
  measurementId: "G-SRZGEB3WSM",
};

export default { API_BASE_URL, FIREBASE_CONFIG };
// const messaging = firebase.messaging();
// // Initialize Firebase
// messaging.setBackgroundMessageHandler((payload) => {
//   console.log("[firebase-messaging-sw.js] Received background message ", payload);
//   // Customize notification here
//   const notificationTitle = "Background Message Title";
//   const notificationOptions = {
//     body: "Background Message body.",
//     icon: "/firebase-logo.png",
//   };

//   return self.registration.showNotification(notificationTitle, notificationOptions);
// });
