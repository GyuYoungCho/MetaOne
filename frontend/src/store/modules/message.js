export default {
  namespaced: true,
  state: {
    selectmessage: Object,
    selectreceiver: String,
    mymessages: [],
    onebyonemessages: [],
    onlinelist: [],
  },
  getters: {
    selectmessage(state) {
      return state.selectmessage;
    },
    selectreceiver(state) {
      return state.selectreceiver;
    },
    mymessages(state) {
      return state.mymessages;
    },
    onebyonemessages(state) {
      return state.onebyonemessages;
    },
    onlinelist(state) {
      return state.onlinelist;
    },
  },
  mutations: {
    SELECT_MESSAGE(state, message) {
      state.selectmessage = message;
    },
    SELECT_RECEIVER(state, receiver) {
      state.selectreceiver = receiver;
    },
    SET_MYMESSAGES(state, mymessages) {
      state.mymessages = mymessages;
    },
    SET_ONEBYONEMESSAGES(state, onebyonemessages) {
      state.onebyonemessages = onebyonemessages;
    },
    SET_ONLINELIST(state, onlinelist) {
      state.onlinelist = onlinelist;
    },
  },
  actions: {
    getMessage({ commit }, item) {
      commit("SELECT_MESSAGE", item);
    },
    getReceiver({ commit }, item) {
      commit("SELECT_RECEIVER", item);
    },
    getMyMessages(store) {
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
    getOnebyOneMessages(store) {},
    getOnlineList(store) {},
  },
};
