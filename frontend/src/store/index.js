import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import guestbook from "./modules/guestbook.js";
import message from "./modules/message.js";
import education from "./modules/education.js";
import user from "./modules/user.js";
import process from "./modules/process.js";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  modules: {
    guestbook,
    user,
    message,
    education,
    process,
  },
});
