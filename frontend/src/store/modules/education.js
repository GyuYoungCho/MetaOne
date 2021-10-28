import axios from "@/api/default.js";
import educationAPI from "@/api/education.js";

export default {
  namespaced: true,
  state: {
    selecteducation: Object,
    educations: [],
  },
  getters: {
    selecteducation(state) {
      return state.selecteducation;
    },
    educations(state) {
      return state.educations;
    },
  },
  mutations: {
    SELECT_EDUCATION(state, education) {
      state.selecteducation = education;
    },
    SET_EDUCATIONS(state, educations) {
      state.educations = educations;
    },
  },
  actions: {
    getEducation({ commit }, item) {
      commit("SELECT_EDUCATION", item);
    },
    getEducations(store) {
      axios
        .get(educationAPI.myAttendance())
        .then((res) => {
          store.commit("SET_EDUCATIONS", res.data);
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });
    },
  },
};
