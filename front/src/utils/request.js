import axios from 'axios'
import useToken from '../stores/token'
import {ElLoading} from 'element-plus'
import config from '../config'
import notification from './notification'
import router from '../router'

const baseURL = config.baseURL

var loadingInstance = null

const service = axios.create({baseURL})

service.interceptors.request.use(config => {
    loadingInstance = ElLoading.service()
    const {token} = useToken()
    if (token) {
        config.headers.token = token
    }
    return config
})

service.interceptors.response.use(
    response => {
        return new Promise((resolve, reject) => {
            loadingInstance.close()
            const {code, data, msg} = response.data
            if (code === 200) {
                if (msg) {
                    notification({
                        message: msg,
                        type: 'success'
                    })
                }
                resolve(data)
            } else {
                if (response.request.responseURL === config.baseURL + '/checkalive') {
                    return
                }
                if (code === 403) {
                    router.push({name: 'login'})
                    notification({
                        message: msg,
                        type: 'error'
                    })
                } else {
                    notification({
                        message: msg || '请求失败',
                        type: 'error'
                    })
                }
                reject(msg)
            }
        })
    },
    error => {
        loadingInstance.close()
        notification({
            message: '网络错误',
            type: 'error'
        })
        console.log(error)
    }
)

export default service