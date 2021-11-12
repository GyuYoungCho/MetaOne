export default {
  namespaced: true,
  state: {
    subComplete: true,
    contentBody: "",
    getInstance: false,
    allMap: true,
    chattingOpen: false,
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
    allMap(state) {
      return state.allMap;
    },
    chattingOpen(state) {
      return state.chattingOpen;
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
    SET_ALLMAP(state, status) {
      state.allMap = status;
    },
    SET_CHATTING_OPEN(state, status) {
      state.chattingOpen = status;
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
    getAllMap({ commit }, item) {
      commit("SET_ALLMAP", item);
    },
    getChattingOpen({ commit }, item) {
      commit("SET_CHATTING_OPEN", item);
    },
  },
};
