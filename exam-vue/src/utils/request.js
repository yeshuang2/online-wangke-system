import axios from 'axios'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_URL, // api 的 base_url
  timeout: process.env.VUE_APP_REQUEST_TIME_OUT // 请求超时时间
})

service.interceptors.request.use(config => {
  const token = window.localStorage.getItem('authorization')
  if (token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
    config.headers.authorization = token
  }
  return config
}, error => {
  return Promise.reject(error)
})

// response 拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非200是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code !== 200) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.response.data.errMsg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
