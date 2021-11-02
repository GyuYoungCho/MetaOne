import firebase from "firebase/compat/app";
import "firebase/compat/messaging";
// import "firebase/firebase-messaging"

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
firebase.initializeApp(FIREBASE_CONFIG);

let fire_messaging = null;

if (firebase.messaging.isSupported()) {
  fire_messaging = firebase.messaging();
}

export default firebase.messaging();
