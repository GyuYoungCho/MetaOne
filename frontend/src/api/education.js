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
      data: {
        education: data.education,
      },
    });
  },
  getAttendance(education) {
    return _axios({
      url: `/edu/attendance/${education}`,
      method: "get",
    });
  },
  getCertificate(data) {
    return _axios({
      url: `/edu/certificate`,
      method: "get",
      data: {
        education: data.education,
      },
    });
  },
  updateCertificate(data) {
    return _axios({
      url: `/edu/rank`,
      method: "put",
      data: {
        education: data.education,
        passtime: data.passtime,
      },
    });
  },
  getRank(data) {
    return _axios({
      url: `/edu/rank`,
      method: "get",
      data: {
        education: data.education,
      },
    });
  },
};
