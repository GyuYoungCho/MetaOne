importScripts("https://www.gstatic.com/firebasejs/9.2.0/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging-compat.js");
importScripts("https://www.gstatic.com/firebasejs/9.2.0/firebase-database-compat.js");

const FIREBASE_CONFIG = {
  apiKey: "AIzaSyCKsCv78jJlewnvHKNk30YzevYaaOY74fI",
  authDomain: "favorable-bolt-113915.firebaseapp.com",
  databaseURL: "https://favorable-bolt-113915-default-rtdb.firebaseio.com",
  projectId: "favorable-bolt-113915",
  storageBucket: "favorable-bolt-113915.appspot.com",
  messagingSenderId: "906874790684",
  appId: "1:906874790684:web:a19307cd8665c262bf50d9",
  measurementId: "G-SRZGEB3WSM",
};

let app = null;
let messaging = null;
if (firebase.messaging.isSupported()) {
  app = firebase.initializeApp(FIREBASE_CONFIG);
  messaging = firebase.messaging();
}
