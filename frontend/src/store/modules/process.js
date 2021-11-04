export default {
  namespaced: true,
  state: {
    subComplete: false,
    sendmode: false,
    messSize: 0,
  },
  getters: {
    subComplete(state) {
      return state.subComplete;
    },
    sendmode(state) {
      return state.sendmode;
    },
    messSize(state) {
      return state.messSize;
    },
  },
  mutations: {
    SET_SUBCOMPLETE(state, status) {
      state.subComplete = status;
    },
    SET_SENDMODE(state, status) {
      state.sendmode = status;
    },
    SET_MESSSIZE(state, status) {
      state.messSize = status;
    },
  },
  actions: {
    getSubComplete({ commit }, item) {
      commit("SET_SUBCOMPLETE", item);
    },
    getSendmode({ commit }, item) {
      commit("SET_SENDMODE", item);
    },
    getMessSize({ commit }, item) {
      commit("SET_MESSSIZE", item);
    },
  },
};
