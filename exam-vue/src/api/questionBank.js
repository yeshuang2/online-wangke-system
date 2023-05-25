import request from '@/utils/request'
// 我的题库数据信息
export default {
  getBankHaveQuestionSumByType (queryInfo) {
    return request({
      url: '/public/getBankHaveQuestionSumByType',
      method: 'get',
      params: queryInfo
    })
  },
  deleteQuestionBank (ids) {
    return request({
      url: '/teacher/deleteQuestionBank',
      method: 'get',
      params: ids
    })
  },
  addQuestionBank (questionBank) {
    return request({
      url: '/teacher/addQuestionBank',
      method: 'post',
      data: questionBank
    })
  },
  getQuestionBank () {
    return request({
      url: '/teacher/getQuestionBank',
      method: 'get'
    })
  },
  addBankQuestion (params) {
    return request({
      url: '/teacher/addBankQuestion',
      method: 'get',
      params: params
    })
  },
  removeBankQuestion (params) {
    return request({
      url: '/teacher/removeBankQuestion',
      method: 'get',
      params: params
    })
  },
  getQuestionByBank (params) {
    return request({
      url: '/public/getQuestionByBank',
      method: 'get',
      params: params
    })
  }

}
