export default {
  namespaced: true,
  state: {
    selectmessage: Object,
    selectreceiver: String,
    messages: [],
  },
  getters: {
    selectmessage(state) {
      return state.selectmessage;
    },
    selectreceiver(state) {
      return state.selectreceiver;
    },
    messages(state) {
      return state.messages;
    },
  },
  mutations: {
    SELECT_MESSAGE(state, message) {
      state.selectmessage = message;
    },
    SELECT_RECEIVER(state, receiver) {
      state.selectreceiver = receiver;
    },
    SET_MESSAGES(state, messages) {
      state.messages = messages;
    },
  },
  actions: {
    getMessage({ commit }, item) {
      commit("SELECT_MESSAGE", item);
    },
    getReceiver({ commit }, item) {
      commit("SELECT_RECEIVER", item);
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
