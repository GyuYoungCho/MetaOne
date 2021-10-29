export default {
  sendOne() {
    return "/message/private";
  },
  selectMyMessage() {
    return "/message/private";
  },
  selectOneByOneMessage(nickname) {
    return `/message/private/${nickname}`;
  },
  sendAll() {
    return "/message/public";
  },
  userOnline() {
    return "/message/online";
  },
};
