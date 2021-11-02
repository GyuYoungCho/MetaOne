// import axios from "@/api/default.js";
// import guestbookAPI from "@/api/guestbook.js";

const state = {
    title: "",
    maxPopulation: 4,

}

const actions = {

}

const mutations = {
    SET_JOIN_TITLE(state, payload) {
        state.title = payload;
    },
    SET_JOIN_MAXPOPULATION(state, payload) {
        state.maxPopulation = payload;
    },
}

const getters = {

}

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters,
}