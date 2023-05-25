import request from '@/utils/request'

export default {
    // 视频播放---查询个人视频信息
  getMyGrade (moveId) {
    return request({
        url: `/api/policemen/${moveId}`,
        method: 'get',
    })
  },
  getMovesinfo(params) {
    return request({
      url: '/api/alltablemoves',
      method: 'get',
      params: params
    })
  },
//   删除视频
deleteMovesinfo(moveId) {
    return request({
      url: `/api/deletemove/${moveId}`,
      method: 'get',
    })
  },







}
