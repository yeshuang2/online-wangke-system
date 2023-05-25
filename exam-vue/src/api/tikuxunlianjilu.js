import request from '@/utils/request'

export default {
  // -----记录学生题库训练的记录
  getQuestionXunLianJiLuInfo(params) {
    return request({
      url: '/xunlinajiluinfo/getQuestionXunLianJiLuInfo',
      method: 'get',
      params: params
    })
  },

  // 增加记录学生题库训练的记录
  addQuestionXunLianJiLuInfo(params) {
    return request({
      url: '/xunlinajiluinfo/addQuestionXunLianJiLuInfo',
      method: 'post',
      params: params
    })
  },

  // 记录学生题库的曲线 
  getQuestionXunLianJiLuInfo(params) {
    return request({
      url: '/echarts/getechartsshujutikunxunlian',
      method: 'post',
      params: params
    })
  },

  // 统计学生的视频的时长数据可视化
  getQuestionMovesLieJiInfo(params) {
    return request({
      url: '/echarts/getechartsshujumovesleijiinfo',
      method: 'post',
      params: params
    })
  },

  //  经理部门下的数据学习练习题库的信息
  getbumenshujuiInfo() {
    return request({
      url: '/echarts/getbumengxiadtikuinfo',
      method: 'get',
    })
  },

  //  经理部门下的数据学习练习网课的信息
  getbumenwabgkeshujuiInfo() {
    return request({
      url: '/echarts/getbumengxiadwangkeinfo',
      method: 'get',
    })
  },

  // 行长 统计的题库 all
  gethangzhangtikuInfo() {
    return request({
      url: '/echarts/gethangzhangtikuinfo',
      method: 'get',
    })
  },


  //  行长统计网课 all
  gethangzhangwangkeInfo() {
    return request({
      url: '/echarts/gethangzhangwangkeinfo',
    })
  },


  // 部门情况 --题库的信息
  // 学生会以一个部门或支行为一个团体，学生可以在此查看个人学习情况在所属部门的情况，包括团体排行榜。
  // 同时也可以申请选择目标进行详细成绩上的二人PK比赛。
  getSuoShuBuMenQingKunInfo(params) {
    return request({
      url: '/echarts/getsuoshubumenqingkundatainfo',
      method: 'post',
      params: params
    })
  },

  // 包括团体排行榜---没有汇总进行计算。
  getTuanDuioPaiHangBangInfo() {
    return request({
      url: '/echarts/gettuanduipaihangbangdatainfo',
    })
  },


  // 包括团体排行榜  y汇总进行计算。
  getTuanDuioPaiHangBangHuiZongInfo() {
    return request({
      url: '/echarts/gettuanduipaihangbanghuizhongdatainfo',
    })
  },

  //  学习发展曲线表
  getStudyFaZhanQuXianDataInfo() {
    return request({
      url: '/echarts/getallstudyfazhanquxiantudatainfo',
    })
  },

  // * @param studyUsername  通过当前学生登录的名称
  // * @return   曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
  SelectUserNameAllStudyFuzhiFiveLvnInfo(params) {
    return request({
      url: '/echarts/getselectusernameallstudyfuzhifivelvndatainfo',
      method: 'post',
      params: params
    })
  },

  //   返回所有的数据曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
  getAllStudyFuzhiFiveLvnDataInfo() {
    return request({
      method: 'get',
      url: '/echarts/getallstudyfuzhifivelvndatainfo',
    })
  },


 
  //   结束时间 以这个来记录1周数据（所有的数据 学生进行50%的 计算 给管理员进行查看）
  getAllOneWeekShiXunTuDataInfo() {
    return request({
      method: 'get',
      url: '/echarts/getoneweekshixuntudatainfo',
    })
  },




}
