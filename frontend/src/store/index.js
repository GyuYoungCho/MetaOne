import Vue from "vue";
import Vuex from "vuex";
import guestbook from "./modules/guestbook.js";
import user from "./modules/user.js";
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  guestbook,
  modules: {
    user,
  }
});
