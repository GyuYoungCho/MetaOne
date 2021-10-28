export default {
  myAttendance() {
    return "/edu/attendance";
  },
  registerAttendance() {
    return "/edu/attendance";
  },
  getAttendance(education) {
    return `/edu/attendance/${education}`;
  },
  getCertificate() {
    return "/edu/certificate";
  },
  updateCertificate() {
    return "/edu/rank";
  },
  getRank() {
    return "/edu/rank";
  },
};
