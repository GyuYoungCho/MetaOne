import _axios from "./default.js";

export default {
  regist(data) {
    return _axios({
      url: `/guestbook`,
      method: "post",
      data: {
        content: data,
      },
    });
  },
  select(data) {
    return _axios({
      url: `/guestbook`,
      method: "get",
      params: {
        date: data,
      },
    });
  },
  modify(data) {
    return _axios({
      url: `/guestbook`,
      method: "put",
      data: data,
    });
  },
};
