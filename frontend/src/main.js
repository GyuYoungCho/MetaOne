import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { messaging } from "./api/firebase";

Vue.prototype.$messaging = messaging;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");

window.Kakao.init("6ff43e51601ba8872b2e960886ca9fa3");
