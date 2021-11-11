import axios from "axios";
import store from "@/store/index.js"

const _axios = axios.create({
  baseURL: "https://k5a305.p.ssafy.io:8080/api",
  timeout: 100000,
});

_axios.interceptors.request.use(
  function (config) {
    config.headers['accesstoken'] = store.state.user.accessToken
    return config
  }
)

_axios.interceptors.response.use(
  function (response) {                 // 로그인 성공 시
    if (response.headers.accesstoken) {
      store.commit('user/SET_USER_ACCESSTOKEN', response.headers.accesstoken)
    }
    if (response.headers.refreshtoken) {
      store.commit('user/SET_USER_REFRESHTOKEN', response.headers.refreshtoken)
    }
    return Promise.resolve(response)
  },

  async function (error) {
    // 1. 토큰 만료 시, 토큰 refresh + 기존 요청
    if (error.response.status == 400 && error.response.data.msg == 'needRefreshToken') {
      const originalRequest = error.config
      originalRequest.headers.refreshtoken = store.state.user.refreshToken
      return _axios(originalRequest)
    } else if (error.response.status == 409 && error.response.data.msg == 'refreshTokenExpired') {
      // 2. 액세스, 리프레쉬 모두 만료 시 로그아웃
      store.dispatch('user/logout')
    } else if (error.response.status >= 500) {
      this.$router.push({ name: 'ServerError'})
    }
    return Promise.reject(error)
  }
)

export default _axios;
