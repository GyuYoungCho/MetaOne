import _axios from './default.js'

export default {
    registerUser(data) {

        return _axios({
            url: `/users`,
            method: 'post',
            data: data
        })
    },
    checkDuplicate(data, type) {
        
        return _axios({
            url: `/users/duplicate`,
            method: 'get',
            params: {
                type: type,
                data: data
            }
        })
    },
    checkEmail(data) {
        return _axios({
            url: `/users/email`,
            method: 'post',
            data: data
        })
    },
    authenticateNumber(data) {

        return _axios({
            url: `/users/email-check`,
            method: 'post',
            data: {
                code: data.authNumber,
                email: data.email,
            }
        })
    },
    login(data) {
        
        return _axios({
            url: `/users/login`,
            method: 'post',
            data: data,
        })
    },
    getMyInfo(data) {
        
        return _axios({
            url: `/users/my-info`,
            method: 'get',
        })
    },
    updateInfo(data) {
        
        return _axios({
            url: `/users`,
            method: 'put',
            data: {
                nickname: data.nickname,
                originPassword: data.originPassword,
                newPassword: data.newPassword,
            }
        })
    }
}