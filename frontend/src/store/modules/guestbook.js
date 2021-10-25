import axios from "@/api/default.js";
import guestbookAPI from "@/api/guestbook.js";

export default {
  namspaced: true,
  state: {
    guestbooks: [],
  },
  getters: {
    guestbooks(state) {
      return state.guestbooks;
    },
  },
  mutations: {
    SET_GUESTBOOKS(state, guestbooks) {
      state.guestbooks = guestbooks;
    },
  },
  actions: {
    getGuestbooks(store) {
      axios
        .get(guestbookAPI.select())
        .then((res) => {
          store.commit("SET_GUESTBOOKS", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
  },
};
