import guestbookAPI from "@/api/guestbook.js";

export default {
  namespaced: true,
  state: {
    guestbooks: [],
    selectguestbook: Object,
  },
  getters: {
    guestbooks(state) {
      return state.guestbooks;
    },
    selectguestbook(state) {
      return state.selectguestbook;
    },
  },
  mutations: {
    SET_GUESTBOOKS(state, guestbooks) {
      state.guestbooks = guestbooks;
    },
    SELECT_GUESTBOOK(state, guestbook) {
      state.selectguestbook = guestbook;
    },
  },
  actions: {
    async getGuestbooks({ commit }, data) {
      await guestbookAPI
        .select(data)
        .then((res) => {
          commit("SET_GUESTBOOKS", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    selectGuestbook({ commit }, item) {
      commit("SELECT_GUESTBOOK", item);
    },
  },
};
