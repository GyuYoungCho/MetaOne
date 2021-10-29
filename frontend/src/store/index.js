import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import guestbook from "./modules/guestbook.js";
import join from "./modules/join.js";
import message from "./modules/message.js";
import education from "./modules/education.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    guestbook,
    join,
    message,
    education,
  },
});

export default store;
