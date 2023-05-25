import request from '@/utils/request'

export default {
  getMenuInfo () {
    return request({
      url: '/common/getMenu',
      method: 'get'
    })
  }
}
