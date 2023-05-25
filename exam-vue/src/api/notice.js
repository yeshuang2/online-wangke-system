import request from '@/utils/request'

export default {
  getCurrentNewNotice () {
    return request({
      url: '/public/getCurrentNewNotice'
    })
  },
  getAllNotice (params) {
    return request({
      url: '/admin/getAllNotice',
      method: 'get',
      params: params
    })
  },
  deleteNotice (params) {
    return request({
      url: '/admin/deleteNotice',
      method: 'get',
      params: params
    })
  },
  publishNotice (data) {
    return request({
      url: '/admin/publishNotice',
      method: 'post',
      data: data
    })
  },
  updateNotice (data) {
    return request({
      url: '/admin/updateNotice',
      method: 'post',
      data: data
    })
  }
}
