import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
// 引入echarts

import axiosService from '@/utils/request'
import * as echarts from 'echarts'

Vue.prototype.$request = axiosService
Vue.prototype.$echarts = echarts

//配置请求根路径
axios.defaults.baseURL = process.env.VUE_APP_BASE_URL
//axios拦截器拦截每一个请求,有token就配置头信息的token
axios.interceptors.request.use(config => {
  let token = window.localStorage.getItem('authorization')
  if (token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
    config.headers.authorization = token
  }
  return config
}, error => {
  return Promise.reject(error)
})
Vue.prototype.$http = axios
Vue.config.productionTip = false

//全局过滤器(秒数转化为分钟)
Vue.filter('timeFormat',function (time) {
  //分钟
  var minute = time / 60;
  var minutes = parseInt(minute);

  if (minutes < 10) {
    minutes = "0" + minutes;
  }

  //秒
  var second = time % 60;
  var seconds = Math.round(second);
  if (seconds < 10) {
    seconds = "0" + seconds;
  }
  return `${minutes}:${seconds}`;
})

//解决路径跳转的报错
import Router from 'vue-router'

const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
