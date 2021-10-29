import joinApi from "@/api/join.js";

const state = {
    name: "",
    email: "",
    nickname: "",
    password: "",
    passwordConfirm: "",
    authNumber: "",

    originPassword: "",

    emailFormat: false,
    emailPass: false,
    nicknamePass: false,
}

const actions = {
    async registerUser({ state }) {
        await joinApi.registerUser(state)
            .then((res) => {
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
                // if (err.response.status == 404) {
                //     router.push({name: 'PageNotFound'})
                // }
        })
    },
    async checkDuplicate({ state }, type) {
        let data = ""
        if (type == 'nickname') data = state.nickname
        else if(type == 'email') data = state.email

        await joinApi.checkDuplicate(data, type)
            .then((res) => {
                console.log(res)
                if (res.response.status == 200) data = true;
                else data = false;
            })
            .catch((err) => {
                console.log(err)
                data = false;
            })
    }
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

    SET_JOIN_NICKNAMEPASS(state, payload) {
        state.nicknamePass = payload;
    },
    SET_JOIN_EMAILPASS(state, payload) {
        state.emailPass = payload;
    },
    SET_JOIN_EMAILFORMAT(state, payload) {
        state.emailFormat = payload;
    },
    
}

const getters = {
    getEmailFormat: (state) => {
        return state.emailFormat;
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters,
}