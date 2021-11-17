import unityApi from "@/api/unity.js";
// import router from "@/router";

const state = {
  unityObject: "",
  unityCharacter: "",
  unityRoom: "",
  roomid: "",
  unityEducationName: "", //
  unityEducationTime: 0,
  unityEducationAuth: false,
};

const actions = {
  async setCharacter({ state }) {
    await unityApi
      .setCharacter(state)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  async setEducationTime({ state }) {
    await unityApi
      .setEducationTime(state)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};

const mutations = {
  SET_UNITY_OBJECT(state, payload) {
    state.unityObject = payload;
  },
  SET_UNITY_CHARACTER(state, payload) {
    state.unityCharacter = payload;
  },
  SET_UNITY_ROOM(state, payload) {
    state.unityRoom = payload;
  },
  SET_UNITY_ROOMID(state, payload) {
    state.roomid = payload;
  },
  SET_UNITY_EDUCATIONNAME(state, payload) {
    state.unityEducationName = payload;
  },
  SET_UNITY_EDUCATIONTIME(state, payload) {
    state.unityEducationTime = payload;
  },
  SET_UNITY_EDUCATIONAUTH(state, payload) {
    state.unityEducationAuth = payload;
  },
};

const getters = {};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};
