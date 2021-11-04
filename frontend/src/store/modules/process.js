export default {
  namespaced: true,
  state: {
    subComplete: false,
  },
  getters: {
    subComplete(state) {
      return state.subComplete;
    },
  },
  mutations: {
    SET_SUBCOMPLETE(state, status) {
      state.subComplete = status;
    },
  },
  actions: {
    getSubComplete({ commit }, item) {
      commit("SET_SUBCOMPLETE", item);
    },
  },
};
