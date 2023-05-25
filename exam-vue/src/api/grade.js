import request from '@/utils/request'

export default {
  getMyGrade (query) {
    return request({
      url: '/student/getMyGrade',
      method: 'get',
      params: query
    })
  }
}
