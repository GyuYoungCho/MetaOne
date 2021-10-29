export default {
  namespaced: true,
  state: {
    selectmessage: Object,
    messages: [],
  },
  getters: {
    selectmessage(state) {
      return state.selectmessage;
    },
    messages(state) {
      return state.messages;
    },
  },
  mutations: {
    SELECT_MESSAGE(state, message) {
      state.selectmessage = message;
    },
    SET_MESSAGES(state, messages) {
      state.messages = messages;
    },
  },
  actions: {
    getMessage({ commit }, item) {
      commit("SELECT_MESSAGE", item);
    },
    getMessages(store) {
      // axios
      //   .get(messageAPI.selectMyMessage())
      //   .then((res) => {
      //     store.commit("SET_MESSAGES", res.data);
      //   })
      //   .catch((error) => {
      //     alert("못가져옴");
      //     console.log(error);
      //   });
    },
  },
};
