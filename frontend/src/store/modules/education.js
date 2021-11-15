import educationAPI from "@/api/education.js";

export default {
  namespaced: true,
  state: {
    selecteducation: Object,
    educations: [],
    certificate: Object,
    rank: [],
    edunum: 0,
  },
  getters: {
    selecteducation(state) {
      return state.selecteducation;
    },
    educations(state) {
      return state.educations;
    },
    certificate(state) {
      return state.certificate;
    },
  },
  mutations: {
    SELECT_EDUCATION(state, education) {
      state.selecteducation = education;
    },
    SET_EDUCATIONS(state, educations) {
      state.educations = [];
      educations.forEach((item) => {
        state.educations.push(item);
      });
    },
    SET_CERTIFICATE(state, certificate) {
      state.certificate = certificate;
    },

    SET_RANK(state, payload) {
      state.rank = payload;
    },

    SET_EDUNUM(state, payload) {
      state.edunum = payload;
    },
  },
  actions: {
    getEducation({ commit }, item) {
      commit("SELECT_EDUCATION", item);
    },

    async getEducations({ commit }) {
      await educationAPI
        .myAttendance()
        .then((res) => {
          commit("SET_EDUCATIONS", res.data.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    getCertificate({ commit }, item) {
      commit("SET_CERTIFICATE", item);
    },
    async getRank({ commit }, kind) {
      await educationAPI
        .getRank(kind)
        .then((res) => {
          console.log(res);

          let rank = [];
          for (let i = 0; i < res.data.data.length; i++) {
            rank[i] = res.data.data[i];
          }
          commit("SET_RANK", rank);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getEdunum({ commit }, item) {
      commit("SET_EDUNUM", item);
    },
  },
};
