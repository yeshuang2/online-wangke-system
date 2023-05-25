import request from '@/utils/request'

export default {
  getRoleInfo () {
    return request({
      url: '/admin/getRole',
      method: 'get'
    })
  }
}
