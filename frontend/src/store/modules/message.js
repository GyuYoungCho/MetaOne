import messageAPI from "@/api/message.js";

export default {
  namespaced: true,
  state: {
    sendmode: false,
    allmode: false,
    messSize: 0,
    selectmessage: Object,
    selectreceiver: String,
    mymessages: [],
    onebyonemessages: [],
    onlinelist: [],
  },
  getters: {
    sendmode(state) {
      return state.sendmode;
    },
    allmode(state) {
      return state.allmode;
    },
    messSize(state) {
      return state.messSize;
    },
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
    SET_SENDMODE(state, status) {
      state.sendmode = status;
    },
    SET_ALLMODE(state, status) {
      state.allmode = status;
    },
    SET_MESSSIZE(state, status) {
      state.messSize = status;
    },
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
      state.onlinelist = [];
      if (onlinelist) {
        onlinelist.forEach((item) => {
          if (item.online) state.onlinelist.push(item);
        });
      }
    },
  },
  actions: {
    getSendmode({ commit }, item) {
      commit("SET_SENDMODE", item);
    },
    getAllmode({ commit }, item) {
      commit("SET_ALLMODE", item);
    },
    getMessSize({ commit }, item) {
      commit("SET_MESSSIZE", item);
    },
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
