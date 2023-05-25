import request from '@/utils/request'

export default {
  getQuestionById (questionId) {
    return request({
      url: `/student/getQuestionById/${questionId}`,
      method: 'get'
    })
  },
  getQuestionByIds (questionIds) {
    return request({
      url: `/student/getQuestionByIds`,
      params: questionIds,
      method: 'get'
    })
  },
  getQuestion (queryInfo) {
    return request({
      url: '/teacher/getQuestion',
      method: 'get',
      params: queryInfo
    })
  },
  deleteQuestion (questionIds) {
    return request({
      url: '/teacher/deleteQuestion',
      method: 'get',
      params: questionIds
    })
  },
  addQuestion (question) {
    return request({
      url: '/teacher/addQuestion',
      method: 'post',
      data: question
    })
  },
  updateQuestion (question) {
    return request({
      url: '/teacher/updateQuestion',
      method: 'post',
      data: question
    })
  },
  getQuestionByBankIdAndType(params){
    return request({
      url: '/public/getQuestionByBankIdAndType',
      method: 'get',
      params: params
    })
  }
}
