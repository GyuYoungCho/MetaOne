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
}