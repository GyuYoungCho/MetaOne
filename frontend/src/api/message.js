import _axios from "./default.js";

export default {
  sendOne(data) {
    return _axios({
      url: `/message/private`,
      method: "post",
      data: {
        nickname: data.nickname,
        title: data.title,
        content: data.content,
        firebaseToken: data.firebaseToken,
      },
    });
  },
  selectMyMessage() {
    return _axios({
      url: `/message/private`,
      method: "get",
    });
  },
  selectOneByOneMessage(nickname) {
    return _axios({
      url: `/message/private/${nickname}`,
      method: "get",
    });
  },
  readMessage(data) {
    return _axios({
      url: `/message/private`,
      method: "put",
      params: {
        msgId: data,
      },
    });
  },
  userOnline() {
    return _axios({
      url: `/message/online`,
      method: "get",
    });
  },
};
