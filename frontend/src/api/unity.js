import _axios from "./default.js";

export default {
    setCharacter(data) {
        console.log("set character")
        
        return _axios({
            url: `/edu/character`,
            method: "post",
            data: {
                unityCharacter: data.unityCharacter
            }
        })
    },
    setRoom(data) {
        return _axios({
            url: `/edu/room`,
            method: "post",
            data: {
                unityRoom : data.unityRoom
            }
        })
    },
    setRoomPopulation(data) {
        return _axios({
            url: `/edu/room-population`,
            method: "post",
            data: {
                unityRoomPopulation : data.unityRoomPopulation
            }
        })
    },
    setEducationTime(data) {
        return _axios({
            url: `/edu/time`,
            method: "post",
            data: {
                unityEducationTime : data.unityEducationTime
            }
        })
    },
    setEducationAuth(data) {
        return _axios({
            url: `/edu/auth`,
            method: "post",
            data: {
                unityEducationAuth : data.unityEducationAuth
            }
        })
    },
}
