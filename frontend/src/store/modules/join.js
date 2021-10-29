// import axios from "@/api/default.js";
// import guestbookAPI from "@/api/guestbook.js";

const state = {
    name: "",
    email: "",
    nickname: "",
    password: "",
    passwordConfirm: "",
    authNumber: "",

    originPassword: "",

}

const actions = {

}

const mutations = {
    SET_JOIN_NAME(state, payload) {
        state.name = payload;
    },
    SET_JOIN_EMAIL(state, payload) {
        state.email = payload;
    },
    SET_JOIN_NICKNAME(state, payload) {
        state.nickname = payload;
    },
    SET_JOIN_PASSWORD(state, payload) {
        state.password = payload;
    },
    SET_JOIN_PASSWORDCONFIRM(state, payload) {
        state.passwordConfirm = payload;
    },
    SET_JOIN_AUTHNUMBER(state, payload) {
        state.authNumber = payload;
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