import _axios from "./default.js";

export default {
  registerUser(data) {
    return _axios({
      url: `/users`,
      method: "post",
      data: data,
    });
  },
  checkDuplicate(data, type) {
    return _axios({
      url: `/users/duplicate`,
      method: "get",
      params: {
        type: type,
        data: data,
      },
    });
  },
  checkEmail(data) {
    return _axios({
      url: `/users/email`,
      method: "post",
      data: data,
    });
  },
  authenticateNumber(data) {
    return _axios({
      url: `/users/email-check`,
      method: "post",
      data: {
        code: data.authNumber,
        email: data.email,
      },
    });
  },
  login(data) {
    return _axios({
      url: `/users/login`,
      method: "post",
      data: data,
    });
  },
  getMyInfo() {
    return _axios({
      url: `/users/my-info`,
      method: "get",
    });
  },
  updateInfo(data) {
    return _axios({
      url: `/users/my-info`,
      method: "put",
      data: {
        nickname: data.nickname,
        originPassword: data.originPassword,
        newPassword: data.newPassword,
      },
    });
  },
  updateNickname(nickname) {
    return _axios({
      url: `/users/my-info/${nickname}`,
      method: "put",
    });
  },
  sendTempPw(data) {
    return _axios({
      url: `/users/find-pw`,
      method: "post",
      data: {
        toEmail: data.email,
      },
    });
  },
  logout(data) {
    return _axios({
      url: `/users/login`,
      method: "delete",
      params: {
        userId: data.userId,
        firebaseToken: data.firebaseToken,
      },
    });
  },
  kakaoLogin(data) {
    return _axios({
      url: `/users/login-kakao`,
      method: "post",
      data: {
        email: data.email,
        name: data.name,
        firebaseToken: data.firebaseToken,
      },
    });
  },
  comTutorial() {
    return _axios({
      url: `/users/tutorial`,
      method: "put",
    });
  },
};
