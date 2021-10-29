import Vue from "vue";
import Vuex from "vuex";
import guestbook from "./modules/guestbook.js";
import join from "./modules/join.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    guestbook,
    join,
  }
})

export default store
// export default new Vuex.Store({
//   guestbook,
//   join,
// });
