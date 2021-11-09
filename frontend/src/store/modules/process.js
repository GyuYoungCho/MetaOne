export default {
  namespaced: true,
  state: {
    subComplete: true,
    contentBody: "",
  },
  getters: {
    subComplete(state) {
      return state.subComplete;
    },
    contentBody(state) {
      return state.contentBody;
    },
  },
  mutations: {
    SET_SUBCOMPLETE(state, status) {
      state.subComplete = status;
    },
    SET_SUBCONTENTBODY(state, status) {
      state.contentBody = status;
    },
  },
  actions: {
    getSubComplete({ commit }, item) {
      commit("SET_SUBCOMPLETE", item);
    },
    getContentBody({ commit }, item) {
      commit("SET_SUBCONTENTBODY", item);
    },
  },
};
