import educationAPI from "@/api/education.js";

export default {
  namespaced: true,
  state: {
    selecteducation: Object,
    educations: [],
    certificate: Object,
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
      state.educations = educations;
    },
    SET_CERTIFICATE(state, certificate) {
      state.certificate = certificate;
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
          commit("SET_EDUCATIONS", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
    getCertificate({ commit }, item) {
      commit("SET_CERTIFICATE", item);
    },
  },
};
