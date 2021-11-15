import unityApi from "@/api/unity.js";
// import router from "@/router";

const state = {
  unityCharacter: "",
  unityRoom: "",
  unityEducationName: "fire", //
  unityEducationTime: "",
  unityEducationAuth: "",
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
  async setRoom({ state }) {
    await unityApi
      .setRoom(state)
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
  async setEducationAuth({ state }) {
    await unityApi
      .setEducationAuth(state)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};

const mutations = {
  SET_UNITY_CHARACTER(state, payload) {
    state.unityCharacter = payload;
  },
  SET_UNITY_ROOM(state, payload) {
    state.unityRoom = payload;
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
