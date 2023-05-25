import request from '@/utils/request'

export default {
  markExam (params) {
    return request({
      url: '/teacher/setObjectQuestionScore',
      method: 'get',
      params: params
    })
  }
}
