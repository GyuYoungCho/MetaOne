import Vue from "vue";
import Vuex from "vuex";
import guestbook from "./modules/guestbook.js";
import message from "./modules/message.js";
import education from "./modules/education.js";
import certificate from "./modules/certificate.js";

Vue.use(Vuex);

export default new Vuex.Store({
  guestbook,
  message,
  education,
  certificate,
});
