export default {
  namespaced: true,
  state: {
    subComplete: true,
    contentBody: "",
    getInstance: false,
  },
  getters: {
    subComplete(state) {
      return state.subComplete;
    },
    contentBody(state) {
      return state.contentBody;
    },
    getInstance(state) {
      return state.getInstance;
    },
  },
  mutations: {
    SET_SUBCOMPLETE(state, status) {
      state.subComplete = status;
    },
    SET_SUBCONTENTBODY(state, status) {
      state.contentBody = status;
    },
    SET_UNITY_INSTANCE(state, status) {
      state.getInstance = status;
    },
  },
  actions: {
    getSubComplete({ commit }, item) {
      commit("SET_SUBCOMPLETE", item);
    },
    getContentBody({ commit }, item) {
      commit("SET_SUBCONTENTBODY", item);
    },
    getUnityInstance({ commit }, item) {
      commit("SET_UNITY_INSTANCE", item);
    },
  },
};
