import userApi from "@/api/user.js";
import router from "@/router";
import { messaging } from "@/api/firebase.js";

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
  passwordFormat: false,
  passwordConfirmFormat: false,
  nicknameFormat: false,
  nameFormat: false,

  emailPass: false,
  nicknamePass: false,
  emailConfirm: false,

  firebaseToken: "",
  accessToken: "",
  refreshToken: "",

  isLogin: false,
  isKakaoLogin: false,
  isTutorial: false,
};

const actions = {
  async registerUser({ state }) {
    await userApi
      .registerUser(state)
      .then((res) => {
        console.log(res);
        alert("회원가입이 완료되었습니다.");
        router.push({ name: "Login" });
      })
      .catch((err) => {
        console.log(err);
        alert("회원가입에 실패하였습니다.");
        if (err.response.status == 404) {
          router.push({ name: "NotFound" });
        }
      });
  },
  async checkDuplicate({ state }, type) {
    let data = "";
    if (type == "nickname") data = state.nickname;
    else if (type == "email") data = state.email;

    await userApi
      .checkDuplicate(data, type)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          alert("사용할 수 있습니다.");
          if (type == "nickname") state.nicknamePass = true;
          else state.emailPass = true;
        }
      })
      .catch((err) => {
        console.log(err);
        alert("중복되는 정보가 있습니다.");
        if (type == "nickname") state.nicknamePass = false;
        else state.emailPass = false;
      });
  },
  async checkEmail({ state, dispatch }) {
    dispatch("process/getSubComplete", true, { root: true });
    dispatch("process/getContentBody", "인증번호를 보냈습니다! 메일을 확인하세요.", { root: true });

    await userApi.checkEmail(state).then((res) => {
      console.log(res);
      setTimeout(() => {
        alert("인증 번호가 전송되었습니다.");
        dispatch("process/getSubComplete", false, { root: true });
      }, 2000);
    });
  },
  async authenticateNumber({ state }) {
    await userApi
      .authenticateNumber(state)
      .then((res) => {
        console.log(res);
        if (res.data == true) {
          alert("이메일 본인 인증이 완료되었습니다.");
          state.emailConfirm = true;
        }
      })
      .catch((err) => {
        console.log(err);
        alert("인증번호가 잘못되었습니다.");
        state.emailConfirm = false;
      });
  },
  async login({ state, commit, dispatch }) {
    // dispatch("process/getSubComplete", true, { root: true });
    await messaging
      .getToken({ vapidKey: "BHNLrFDYFvHeFVnKkYMskZfNTjOu8z5_G_QQJcIdRZdZ2lq3Sl5iMXRdtDdr_M2fboN1EKU_o-DTsxOBwljmXSY" })
      .then((token) => {
        console.log(token);
        commit("SET_USER_FIREBASETOKEN", token);
      });

    await userApi
      .login(state)
      .then((res) => {
        if (res.status == 200) {
          console.log(res);
          dispatch("process/getSubComplete", true, { root: true });
          dispatch("process/getAllMap", true, { root: true });
          commit("SET_JOIN_USERID", res.data.id);
          commit("SET_JOIN_NAME", res.data.name);
          commit("SET_JOIN_EMAIL", res.data.email);
          commit("SET_JOIN_NICKNAME", res.data.nickname);
          commit("SET_JOIN_TUTORIAL", res.data.tutorial);

          commit("SET_USER_ACCESSTOKEN", res.headers.accesstoken);
          commit("SET_USER_REFRESHTOKEN", res.headers.refreshtoken);
          commit("SET_USER_ISLOGIN", true);

          router.push({ name: "UnityMap" });
        }
      })
      .catch((err) => {
        console.log(err);
        state.password = "";
        alert("로그인 실패!");
        return;
      });
  },
  async getMyInfo({ state, commit }) {
    await userApi
      .getMyInfo(state)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          commit("SET_JOIN_USERID", res.data.id);
          commit("SET_JOIN_NAME", res.data.name);
          commit("SET_JOIN_EMAIL", res.data.email);
          commit("SET_JOIN_NICKNAME", res.data.nickname);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  async updateInfo({ state }) {
    await userApi
      .updateInfo(state)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          alert("성공적으로 수정되었습니다.");
          router.go(); // 새로고침
        }
      })
      .catch((err) => {
        console.log(err);
        alert("기존 비밀번호가 틀렸습니다");
      });
  },
  async updateNickname({ state }) {
    await userApi
      .updateNickname(state.nickname)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          alert("성공적으로 수정되었습니다.");
          router.go();
        }
      })
      .catch((err) => {
        console.log(err);
        alert("닉네임 변경에 실패했습니다.");
      });
  },
  async sendTempPw({ state }) {
    await userApi
      .sendTempPw(state)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
        alert("이름이나 이메일이 틀렸습니다");
      });
  },
  async logout({ state, commit, dispatch }) {
    await userApi
      .logout(state)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
    dispatch("process/getUnityInstance", false, { root: true });
    dispatch("process/getAllMap", true, { root: true });
    commit("SET_JOIN_USERID", "");
    commit("SET_JOIN_NAME", "");
    commit("SET_JOIN_EMAIL", "");
    commit("SET_JOIN_NICKNAME", "");
    commit("SET_JOIN_PASSWORD", "");
    commit("SET_USER_ISLOGIN", false);
    commit("SET_USER_ISKAKAOLOGIN", false);
    commit("SET_USER_ACCESSTOKEN", "");
    commit("SET_USER_REFRESHTOKEN", "");
  },

  async kakaoLogin({ state, commit, dispatch }) {
    await messaging
      .getToken({ vapidKey: "BHNLrFDYFvHeFVnKkYMskZfNTjOu8z5_G_QQJcIdRZdZ2lq3Sl5iMXRdtDdr_M2fboN1EKU_o-DTsxOBwljmXSY" })
      .then((token) => {
        commit("SET_USER_FIREBASETOKEN", token);
      });

    await userApi
      .kakaoLogin(state)
      .then((res) => {
        if (res.status == 200) {
          console.log(res);
          dispatch("process/getSubComplete", true, { root: true });
          dispatch("process/getAllMap", true, { root: true });
          commit("SET_JOIN_USERID", res.data.id);
          commit("SET_JOIN_NAME", res.data.name);
          commit("SET_JOIN_EMAIL", res.data.email);
          commit("SET_JOIN_NICKNAME", res.data.nickname);
          commit("SET_JOIN_TUTORIAL", res.data.tutorial);

          commit("SET_USER_ACCESSTOKEN", res.headers.accesstoken);
          commit("SET_USER_REFRESHTOKEN", res.headers.refreshtoken);
          commit("SET_USER_ISLOGIN", true);
          commit("SET_USER_ISKAKAOLOGIN", true);

          router.push({ name: "UnityMap" });
        }
      })
      .catch((err) => {
        console.log(err);
        state.password = "";
        alert("로그인 실패!");
        return;
      });
  },
};

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
  SET_JOIN_TUTORIAL(state, payload) {
    state.isTutorial = payload;
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
  SET_USER_ISKAKAOLOGIN(state, payload) {
    state.isKakaoLogin = payload;
  },

  SET_JOIN_NICKNAMEPASS(state, payload) {
    state.nicknamePass = payload;
  },
  SET_JOIN_EMAILPASS(state, payload) {
    state.emailPass = payload;
  },
  SET_JOIN_EMAILCONFIRM(state, payload) {
    state.emailConfirm = payload;
  },
  SET_JOIN_NAMEFORMAT(state, payload) {
    state.nameFormat = payload;
  },
  SET_JOIN_NICKNAMEFORMAT(state, payload) {
    state.nicknameFormat = payload;
  },
  SET_JOIN_EMAILFORMAT(state, payload) {
    state.emailFormat = payload;
  },
  SET_JOIN_PASSWORDFORMAT(state, payload) {
    state.passwordFormat = payload;
  },
  SET_JOIN_PASSWORDCONFIRMFORMAT(state, payload) {
    state.passwordConfirmFormat = payload;
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
};

const getters = {
  getEmailFormat: (state) => {
    return state.emailFormat;
  },
  getPasswordFormat: (state) => {
    return state.passwordFormat;
  },
  getNameFormat: (state) => {
    return state.nameFormat;
  },
  getNicknameFormat: (state) => {
    return state.nicknameFormat;
  },
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};
