import userApi from "@/api/user.js";
import router from "@/router"
import messaging from '@/api/firebase.js'

const state = {
    userId: "",
    name: "",
    email: "",
    nickname: "",
    password: "",
    passwordConfirm: "",
    authNumber: "",

    originPassword: "",
    newPassword: "",

    emailFormat: false,
    emailPass: false,
    nicknamePass: false,
    emailConfirm: false,

    firebaseToken: "",
    accessToken: "",
    refreshToken: "",

    isLogin: false,
}

const actions = {
    async registerUser({ state }) {
        await userApi.registerUser(state)
            .then((res) => {
                console.log(res)
                alert("회원가입이 완료되었습니다.")
                router.push({name: 'Login'})
            })
            .catch((err) => {
                console.log(err)
                alert("회원가입에 실패하였습니다.")
                // if (err.response.status == 404) {
                //     router.push({name: 'PageNotFound'})
                // }
        })
    },
    async checkDuplicate({ state }, type) {
        let data = ""
        if (type == 'nickname') data = state.nickname
        else if(type == 'email') data = state.email

        await userApi.checkDuplicate(data, type)
            .then((res) => {
                console.log(res)
                if (res.status == 200) {
                    alert("사용할 수 있습니다.")
                    if (type == 'nickname') state.nicknamePass = true
                    else state.emailPass = true
                }
            })
            .catch((err) => {
                console.log(err)
                alert("중복되는 정보가 있습니다.")
                if (type == 'nickname') state.nicknamePass = false
                else state.emailPass = false
            })
        
        
    },
    async checkEmail({ state }) {
        await userApi.checkEmail(state)
            .then((res) => {
            console.log(res)
        })
    },
    async authenticateNumber({ state }) {
        
        await userApi.authenticateNumber(state)
            .then((res) => {
                console.log(res)
                if (res.data == true) {
                    alert("이메일 본인 인증이 완료되었습니다.")
                    state.emailConfirm = true
                }
            })
            .catch((err) => {
                console.log(err)
                alert("인증번호가 잘못되었습니다.")
                state.emailConfirm = false
        })
    },
    async login({ state, commit }) {
        await messaging.getToken({ vapidKey: 'BHNLrFDYFvHeFVnKkYMskZfNTjOu8z5_G_QQJcIdRZdZ2lq3Sl5iMXRdtDdr_M2fboN1EKU_o-DTsxOBwljmXSY' })
        .then((token) => {
            console.log(token)
            
            commit('SET_USER_FIREBASETOKEN', token)
        })

        await userApi.login(state)
            .then((res) => {
                if (res.status == 200) {
                    console.log(res)
                    commit('SET_JOIN_USERID', res.data.id)
                    commit('SET_JOIN_NAME', res.data.name)
                    commit('SET_JOIN_EMAIL', res.data.email)
                    commit('SET_JOIN_NICKNAME', res.data.nickname)

                    commit('SET_USER_ACCESSTOKEN', res.headers.accesstoken)
                    commit('SET_USER_REFRESHTOKEN', res.headers.refreshtoken)
                    commit('SET_USER_ISLOGIN', true)

                    router.push({name: 'SelectCharacter'})
                }
            })
            .catch((err) => {
                console.log(err)
                state.password = ""
                alert("로그인 실패")
        })
    },
    async getMyInfo({ state, commit }) {

        await userApi.getMyInfo(state)
            .then((res) => {
                console.log(res)
                if (res.status == 200) {
                    commit('SET_JOIN_USERID', res.data.id)
                    commit('SET_JOIN_NAME', res.data.name)
                    commit('SET_JOIN_EMAIL', res.data.email)
                    commit('SET_JOIN_NICKNAME', res.data.nickname)
            }
            })
            .catch((err) => {
            console.log(err)
        })
    },
    async updateInfo({ state }) {

        await userApi.updateInfo(state)
            .then((res) => {
                console.log(res)
                if (res.status == 200) {
                    alert("성공적으로 수정되었습니다.")
                    router.go()                          // 새로고침
                }
            })
            .catch ((err) => {
                console.log(err)
                alert("올바르지 않은 비밀번호입니다.")
        })
    },
    async sendTempPw({ state }) {
        await userApi.sendTempPw(state)
            .then((res) => {
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
        })
    },
    async logout({ state, commit }) {
        
        await userApi.logout(state)
            .then((res) => {
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
        
        commit('SET_JOIN_USERID', "")
        commit('SET_JOIN_NAME', "")
        commit('SET_JOIN_EMAIL', "")
        commit('SET_JOIN_NICKNAME', "")
        commit('SET_JOIN_PASSWORD', "")
        commit('SET_USER_ISLOGIN', false)
        commit('SET_USER_ACCESSTOKEN', "")
        commit('SET_USER_REFRESHTOKEN', "")
        
    },
    onNotification(payload) {
        console.log(payload)
        console.log(payload.notification)

        
        const notiData = {
            type: payload.notification.title,
            data: payload.data
        }
        
        console.log(notiData.type)
        console.log(notiData.data)

    },
}

const mutations = {
    SET_JOIN_USERID(state, payload) {
        state.userId = payload;
    },
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

    SET_USER_ISLOGIN(state, payload) {
        state.isLogin = payload;
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
    
    SET_USER_ACCESSTOKEN(state, payload) {
        state.accessToken = payload;
    },
    SET_USER_REFRESHTOKEN(state, payload) {
        state.refreshToken = payload;
    },
    SET_USER_FIREBASETOKEN(state, payload) {
        state.firebaseToken = payload;
    },

    SET_USER_ORIGINPASSWORD(state, payload) {
        state.originPassword = payload;
    },
    SET_USER_NEWPASSWORD(state, payload) {
        state.newPassword = payload;
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