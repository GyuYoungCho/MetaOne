import VueAxios from "vue-axios";
import axios from "axios";

const BASE_URL = "/api";
const DEFAULT_ACCEPT_TYPE = "application/json";

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers["Content-Type"] = DEFAULT_ACCEPT_TYPE;
axios.defaults.withCredentials = true;

export default { VueAxios, axios };