import _axios from './default.js'

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
  sendAll(data) {
    return _axios({
      url: `/message/public`,
      method: "post",
      data: {
        title: data.title,
        content: data.content,
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
