import _axios from "./default.js";

export default {
  myAttendance() {
    return _axios({
      url: `/edu/attendance`,
      method: "get",
    });
  },
  registerAttendance(data) {
    return _axios({
      url: `/edu/attendance`,
      method: "post",
      params: {
        education: data,
      },
    });
  },
  getAttendance(education) {
    return _axios({
      url: `/edu/attendance/${education}`,
      method: "get",
    });
  },
  getRank(data) {
    console.log("data: ", data);

    return _axios({
      url: `/edu/rank`,
      method: "get",
      params: {
        education: data,
      },
    });
  },
};
