import messageAPI from "@/api/message.js";

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
    async getMyMessages({ commit }) {
      await messageAPI
        .selectMyMessage()
        .then((res) => {
          commit("SET_MYMESSAGES", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    async getOnebyOneMessages({ commit }, item) {
      await messageAPI
        .selectOneByOneMessage(item)
        .then((res) => {
          commit("SET_ONEBYONEMESSAGES", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    async getOnlineList({ commit }) {
      await messageAPI
        .userOnline()
        .then((res) => {
          commit("SET_ONLINELIST", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
  },
};
