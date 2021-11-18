import _axios from "./default.js";

export default {
  setCharacter(data) {
    return _axios({
      url: `/edu/character`,
      method: "post",
      data: {
        unityCharacter: data.unityCharacter,
      },
    });
  },
  setRoom(data) {
    return _axios({
      url: `/edu/room`,
      method: "post",
      data: {
        unityRoom: data.unityRoom,
      },
    });
  },

  setEducationTime(data) {
    return _axios({
      url: `/edu/time`,
      method: "post",
      data: {
        unityEducationTime: data.unityEduTime,
        unityEducation: data.unityEduName,
      },
    });
  },
  setEducationAuth(data) {
    return _axios({
      url: `/edu/auth`,
      method: "post",
      data: {
        unityEducationAuth: data.unityEducationAuth,
        unityEducation: data.unityEducationName,
      },
    });
  },
};
