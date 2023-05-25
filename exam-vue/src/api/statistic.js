import request from '@/utils/request'

export default {
  getExamPassRate () {
    return request({
      url: '/teacher/getExamPassRate',
      method: 'get'
    })
  },
  getExamNumbers () {
    return request({
      url: '/teacher/getExamNumbers',
      method: 'get'
    })
  }
}
