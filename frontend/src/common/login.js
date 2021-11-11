import axios from "axios";

const kakaoHeader = {
  Authorization: "ca7c90d1ca4f709843860d53ddf7cbb7",
  "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
};

const getKakaoToken = async (code) => {
  console.log("loginWithKakao");
  try {
    const data = {
      grant_type: "authorization_code",
      client_id: "d38241883415ac9ef343f853cde053ff",
      redirect_uri: "http://localhost:8000/auth",
      code: code,
    };
    const queryString = Object.keys(data)
      .map((k) => encodeURIComponent(k) + "=" + encodeURIComponent(data[k]))
      .join("&");
    const result = await axios.post("https://kauth.kakao.com/oauth/token", queryString, { headers: kakaoHeader });
    console.log("카카오 토큰", queryString);
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
  console.log("카카오 계정 정보", data);
  return data;
};

const setKakaoUserInfo = (data) => {
  const result = axios.post("/api/users/login-kakao", {
    email: data.email,
    name: data.name,
  });
  return result;
};

export { getKakaoToken, getKakaoUserInfo, setKakaoUserInfo };
