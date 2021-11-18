import axios from "axios";
import BASE from "@/api/client.js";

const kakaoHeader = {
  Authorization: "ca7c90d1ca4f709843860d53ddf7cbb7",
  "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
};

const getKakaoToken = async (code) => {
  try {
    const data = {
      grant_type: "authorization_code",
      client_id: "d38241883415ac9ef343f853cde053ff",
      redirect_uri: BASE.url + "/auth",
      code: code,
    };
    const queryString = Object.keys(data)
      .map((k) => encodeURIComponent(k) + "=" + encodeURIComponent(data[k]))
      .join("&");
    const result = await axios.post("https://kauth.kakao.com/oauth/token", queryString, { headers: kakaoHeader });
    return result;
  } catch (e) {
    return e;
  }
};

const getKakaoUserInfo = async () => {
  let data = "";
  await window.Kakao.API.request({
    url: "/v2/user/me",
    success: function (response) {
      data = response;
    },
    fail: function (error) {
      console.log(error);
    },
  });
  return data;
};

export { getKakaoToken, getKakaoUserInfo };
