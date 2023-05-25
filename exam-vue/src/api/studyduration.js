
import request from '@/utils/request'

export default {
  // 查询所有学习记录------网课的记录信息
  getStudyDurationInfo (params) {
    return request({
      url: '/studyduration/getStudydurationInfo',
      method: 'get',
      params: params
    })
  },

// 增加学生学习记录
  addStudyDurationInfoInfo (params) {
    return request({
      url: '/studyduration/addStudydurationInfo',
      method: 'post',
      params: params
    })
  },

  // 更新
  updateStudyDurationInfo (data) {
    return request({
      url: '/studyduration/updateStudyDurationInfoById',
      method: 'post',
      data: data
    })
  },


// 查看视频的接口状态数据
  getStudyMovesZtInfo (params) {
    return request({
      url: '/echarts/getoneMovesZTinfo',
      method: 'get',
      params: params
    })
  },

}