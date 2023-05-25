import request from '@/utils/request'

export default {
  getCurrentUser () {
    return request({
      url: '/common/getCurrentUser',
      method: 'get'
    })
  },
  updateCurrentUser (updateUser) {
    return request({
      url: '/common/updateCurrentUser',
      method: 'post',
      data: updateUser
    })
  },
  getUserById (userId) {
    return request({
      url: `/teacher/getUserById/${userId}`,
      method: 'get'
    })
  },
  getUserByIds (userIds) {
    return request({
      url: `/teacher/getUserByIds`,
      params: userIds,
      method: 'get'
    })
  },
  getUserInfo (params) {
    return request({
      url: '/admin/getUser',
      method: 'get',
      params: params
    })
  },
// 查询个人信息数据
getUserByIdInfo (userId) {
  return request({
    url: `/admin/getUserById/${userId}`,
    method: 'get',
  })
},
// 更新
updateUser(updateUser) {
  return request({
    url: '/admin/updateuser',
    method: 'post',
    data: updateUser
  })
},

  handlerUser (operateId, params) {
    return request({
      url: `/admin/handleUser/${operateId}`,
      method: 'get',
      params: params
    })
  },
  addUser (data) {
    return request({
      url: '/admin/addUser',
      method: 'post',
      data: data
    })
  }

}
