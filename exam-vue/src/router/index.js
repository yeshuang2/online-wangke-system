import Vue from 'vue'
import VueRouter from 'vue-router'
import axios from 'axios'

import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css' // Progress 进度条样式
// 进度条配置项
NProgress.configure({
  showSpinner: false
})

Vue.use(VueRouter)

const routes = [{
    path: '/',
    component: () => import('../views/auth/Login')
  },
  {
    path: '/register',
    component: () => import('../views/auth/Register')
  },
  {
    path: '/index',
    component: () => import('../views/index/Main'),
    redirect: '/dashboard',
    children: [
      //仪表盘介绍(all)
      {
        path: '/dashboard',
        component: () => import('../views/index/Dashboard')
      },
      //用户管理(超级管理员)
      {
        path: '/userManage',
        component: () => import('../views/admin/UserManage')
      },
      //角色信息(超级管理员)
      {
        path: '/roleManage',
        component: () => import('../views/admin/RoleManage')
      },
      //题库管理(老师和超级管理员)
      {
        path: '/questionManage',
        component: () => import('../views/teacher/QuestionManage')
      },
      //题库管理(老师和超级管理员)
      {
        path: '/questionBankMange',
        component: () => import('../views/teacher/QuestionBankManage')
      },
      //我的题库(all)
      {
        path: '/myQuestionBank',
        component: () => import('../views/student/MyQuestionBank')
      },
      //题库训练页(学生和管理员)
      {
        path: '/train/:bankId/:trainType',
        name: 'trainPage',
        component: () => import('../views/student/TrainPage')
      },
      //考试管理(老师和超级管理员)
      {
        path: '/examManage',
        component: () => import('../views/teacher/ExamManage')
      },
      //添加考试(老师和超级管理员)
      {
        path: '/addExam',
        component: () => import('../views/teacher/AddExam')
      },
      //修改考试信息(老师和超级管理员)
      {
        path: '/updateExam/:examId',
        name: 'updateExam',
        component: () => import('../views/teacher/UpdateExam')
      },
      //在线考试页面选择考试(学生和超级管理员)
      {
        path: '/examOnline',
        component: () => import('../views/student/ExamOnline')
      },
      //考试结果页(学生和超级管理员)
      {
        path: '/examResult/:recordId',
        name: 'examResult',
        component: () => import('../views/student/ExamResult')
      },
      //阅卷管理页面(老师和超级管理员)
      {
        path: '/markManage',
        component: () => import('../views/teacher/MarkManage')
      },
      //批阅试卷(老师和管理员)
      {
        path: '/markExam/:recordId',
        name: 'markExam',
        component: () => import('../views/teacher/MarkExamPage')
      },
      //我的成绩(学生和管理员)
      {
        path: '/myGrade',
        component: () => import('../views/student/MyGrade')
      },
      //统计总览页面(老师和管理员)
      {
        path: '/staticOverview',
        component: () => import('../views/teacher/StatisticOverview')
      },
      // 公告管理(管理员)
      {
        path: '/noticeManage',
        component: () => import('../views/admin/NoticeManage')
      },
      // all 看公告信息
      {
        path: '/noticeManageall',
        component: () => import('../views/student/NoticeManageALL')
      },
      // 新增加需求
      //网课管理
      {
        path: '/wangkeManage',
        component: () => import('../views/admin/WangkeManage')
      },
      //网课播放
      {
        path: '/wangkebofang',
        component: () => import('../views/admin/Wangkebofang')
      },
      // 题库进行分配给用户不同的业务
      {
        path: '/yuangongyewufepeiManage',
        component: () => import('../views/admin/YuanGongYeWuFePeiManage')
      },
      // 网课分类添加
      {
        path: '/wangkeCategorizeManage',
        component: () => import('../views/admin/WangkeCategorizeManage')
      },
      // 学生端学习网课

      {
        path: '/myfeileiwangkeBank',
        component: () => import('../views/student/MyFeileiWangKeBank')
      },
      //学生的学习情况曲线图界面(管理员和学生)
      {
        path: '/mystudygraphTu',
        component: () => import('../views/student/MyStudyGraphTu')
      },
      //学生的学习moves 视频情况曲线图界面(管理员和学生)
      {
        path: '/mystudymovesgraphTu',
        component: () => import('../views/student/MystudyMovesGraphTu')
      },

      //  部门经理查看题库    部门下的学习情况数据
      {
        path: '/bumtikustudyshujuMange',
        component: () => import('../views/teacher/BuMTiKuStudyShuJuMange')
      },
      //  部门经理查看网课   部门下的学习情况数据
      {
        path: '/bumwangkestudyshujuMange',
        component: () => import('../views/teacher/BuMWangKeStudyShuJuMange')
      },
      //管理员--》  行长查看题库情况

      {
        path: '/allstudytikudata',
        component: () => import('../views/admin/AllStudyTiKuData')
      },
      //  管理员--》 行长查看视频情况
      {
        path: '/allstudymovesdata',
        component: () => import('../views/admin/AllStudyMovesData')
      },
      //  管理员--》 学习发展曲线表
      {
        path: '/allstudyfazhanquxiandata',
        component: () => import('../views/admin/AllStudyFaZhanQuXianData')
      },

      //部门情况======所属部门情况 查看个人学习情况在所属部门的情况
      {
        path: '/suoshubumenqingkun',
        component: () => import('../views/student/SuoShuBuMenQingKun')
      },
      //部门情况-======团队排行榜
      {
        path: '/tuanduiopaihangbang',
        component: () => import('../views/student/TuanDuioPaiHangBang')
      },


   //曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
   {
    path: '/allstudyfuzhifivelvndata',
    component: () => import('../views/admin/AllStudyFuzhiFiveLvnData')
  },

  //  我的成绩情况分析 带参数
   //曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
   {
    path: '/mystudycjqingkunfx',
    component: () => import('../views/student/MyStudyCJQingKunFX')
  },

  {
    path: '/alloneweekshixuntudata',
    component: () => import('../views/admin/AllOneWeekShiXunTuData')
  },

  // 我的信息 myxinxiinfo
  {
    path: '/myxinxiinfo',
    component: () => import('../views/student/MyXinXiInfo')
  },

    ]
  },
  //考试界面(管理员和学生)
  {
    path: '/exam/:examId',
    name: 'exam',
    component: () => import('../views/student/ExamPage')
  },

]

const router = new VueRouter({
  routes
})
router.beforeEach((to, from, next) => {
  NProgress.start()
  const token = window.localStorage.getItem('authorization')
  //2个不用token的页面请求
  if (to.path === '/' || to.path === '/register') {
    return next()
  }
  //没有token的情况 直接返回登录页
  if (!token) return next('/')
  //属于超级管理员的功能
  if (to.path === '/userManage' || to.path === '/roleManage' || to.path === '/noticeManage') {
    axios.get('/common/checkToken').then((resp) => {
      if (resp.data.code === 200 && resp.data.data.roleId === 3) { //当前用户携带的token信息正确并且是管理员
        next()
      }
    }).catch(err => {
      this.$notify({
        title: 'Tips',
        message: err.response.data.errMsg,
        type: 'error',
        duration: 2000
      })
      localStorage.removeItem('authorization')
      return next('/index')
    })
  }
  //属于超级管理员又属于老师
  if (to.path === '/questionManage' || to.path === '/questionBankMange' || to.path === '/examManage' ||
    to.path === '/addExam' || to.name === 'updateExam' || to.path === '/markManage' || to.name === 'markExam') {
    axios.get('/common/checkToken').then((resp) => {
      if (resp.data.code === 200 && resp.data.data.roleId === 3 || resp.data.data.roleId === 2) {
        next()
      }
    }).catch(err => {
      this.$notify({
        title: 'Tips',
        message: err.response.data.errMsg,
        type: 'error',
        duration: 2000
      })
      localStorage.removeItem('authorization')
      return next('/index')
    })
  }

  //超级管理员 + 学生
  if (to.path === '/myQuestionBank' || to.name === 'trainPage' || to.path === '/examOnline' ||
    to.name === 'exam' || to.name === 'examResult' || to.path === '/myGrade' || to.path === '/myfeileiwangkeBank') {
    axios.get('/common/checkToken').then((resp) => {
      if (resp.data.code === 200 && resp.data.data.roleId !== 2) {
        next()
      }
    }).catch(err => {
      this.$notify({
        title: 'Tips',
        message: err.response.data.errMsg,
        type: 'error',
        duration: 2000
      })
      localStorage.removeItem('authorization')
      return next('/index')
    })
  }
  next()
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})

export default router
