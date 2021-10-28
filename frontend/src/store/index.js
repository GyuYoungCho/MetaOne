import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import guestbook from "./modules/guestbook.js";
import message from "./modules/message.js";
import education from "./modules/education.js";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState(guestbook, message, education)],
  modules: {
    guestbook,
    message,
    education,
  },
});
