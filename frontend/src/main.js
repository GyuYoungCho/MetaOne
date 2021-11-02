import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import { initializeApp } from "firebase/app";
// import { getDatabase } from "firebase/database";
// import { FIREBASE_CONFIG } from "./config";
// const FIREBASE_CONFIG = {
//   apiKey: "AIzaSyCKsCv78jJlewnvHKNk30YzevYaaOY74fI",
//   authDomain: "favorable-bolt-113915.firebaseapp.com",
//   databaseURL: "https://favorable-bolt-113915-default-rtdb.firebaseio.com",
//   projectId: "favorable-bolt-113915",
//   storageBucket: "favorable-bolt-113915.appspot.com",
//   messagingSenderId: "906874790684",
//   appId: "1:906874790684:web:a19307cd8665c262bf50d9",
//   measurementId: "G-SRZGEB3WSM",
// };

// const iapp = initializeApp(FIREBASE_CONFIG);
// const db = getDatabase(iapp);

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");

window.Kakao.init("6ff43e51601ba8872b2e960886ca9fa3");
