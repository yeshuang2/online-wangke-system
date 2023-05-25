import request from '@/utils/request'

export default {
  getExamInfo (queryInfo) {
    return request({
      url: '/public/getExamInfo',
      method: 'post',
      data: queryInfo
    })
  },
  allExamInfo () {
    return request({
      url: '/public/allExamInfo',
      method: 'get'
    })
  },
  operationExam (operateId, params) {
    return request({
      url: `/teacher/operationExam/${operateId}`,
      method: 'get',
      params: params
    })
  },
  getExamRecord (params) {
    return request({
      url: '/teacher/getExamRecord',
      method: 'get',
      params: params
    })
  },
  getExamInfoById (params) {
    return request({
      url: '/public/getExamInfoById',
      method: 'get',
      params: params
    })
  },
  addExamRecord (data) {
    return request({
      url: '/student/addExamRecord',
      method: 'post',
      data: data
    })
  },
  getExamRecordById (recordId) {
    return request({
      url: `/student/getExamRecordById/${recordId}`,
      method: 'get'
    })
  },
  getExamQuestionByExamId (examId) {
    return request({
      url: `/student/getExamQuestionByExamId/${examId}`,
      method: 'get'
    })
  },
  addExamByBank (data) {
    return request({
      url: `/teacher/addExamByBank`,
      method: 'post',
      data: data
    })
  },
  addExamByQuestionList (data) {
    return request({
      url: '/teacher/addExamByQuestionList',
      method: 'post',
      data: data
    })
  },
  updateExamInfo(data){
    return request({
      url: '/teacher/updateExamInfo',
      method: 'post',
      data: data
    })
  }
}
