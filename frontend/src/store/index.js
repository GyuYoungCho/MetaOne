import Vue from "vue";
import Vuex from "vuex";
import guestbook from "./modules/guestbook.js";

Vue.use(Vuex);

export default new Vuex.Store({
  guestbook,
});
