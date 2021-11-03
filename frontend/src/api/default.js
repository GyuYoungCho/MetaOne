import axios from "axios";
import store from "@/store/index.js"

const _axios = axios.create({
  baseURL: "http://k5a305.ssafy.io:8080/api",
  timeout: 100000,
});

_axios.interceptors.request.use(
  function (config) {
    config.headers['accesstoken'] = store.state.user.accessToken
    return config
  }
)

export default _axios;
