import request from '@/utils/request'
// 我的题库数据信息
export default {
      getMovesBank () {
        return request({
          url: '/wangke/getMovesBankInfo',
          method: 'get'
        })
      },

      // 添加
      addMovesBank (data) {
        return request({
          url: '/wangke/addBankInfo',
          method: 'post',
          data: data
        })
      },


      //删除
      deleteovesBank (params) {
        return request({
          url: '/wangke/deleteBankInfoById',
          method: 'get',
          params: params
        })
      },


      // 更新
      updateMovesBank (data) {
        return request({
          url: '/wangke/updateBankInfoById',
          method: 'post',
          data: data
        })
      },

      // 角色业务分配管理系统
     // 添加
     addQuestTionYeWuInfo (params) {
      return request({
        url: '/fepeiyewu/addQuestTionYeWuInfo',
        method: 'post',
        params: params
      })
    },


    // 查询当前用户所属的业务和银行
    getYonghuWangkeYeWuBankInfo (params) {
      return request({
        url: '/wangke/getYonghuWangkeYeWuBankInfo',
        method: 'get',
        params: params
      })
    },




}