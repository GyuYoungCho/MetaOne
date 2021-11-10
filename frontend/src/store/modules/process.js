export default {
  namespaced: true,
  state: {
    subComplete: true,
    contentBody: "",
    unityInstance: null,
  },
  getters: {
    subComplete(state) {
      return state.subComplete;
    },
    contentBody(state) {
      return state.contentBody;
    },
    unityInstance(state) {
      return state.unityInstance;
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
      state.unityInstance = status;
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
