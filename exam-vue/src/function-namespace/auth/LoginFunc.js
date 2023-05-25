import auth from '@/api/auth'
import utils from '@/utils/utils'
import router from '../../router/index'
import Vue from 'vue'

// 登录表单数据信息
const loginForm = {
  username: '',
  password: '12345',
  // 验证码
  code: ''
}
// 自定义验证码校验规则
const validateCode = (rule, value, callback) => {
  // 验证码不区分大小写
  if (value.toString().toLocaleLowerCase() !== code.toString().toLocaleLowerCase()) {
    callback(new Error('验证码输入错误'))
  } else {
    callback()
  }
}
// 登录表单的校验规则
const loginFormRules = {
  username: [
    {
      required: true,
      message: '请输入账号',
      trigger: 'blur'
    },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    },
    {
      min: 5,
      message: '密码不能小于5个字符',
      trigger: 'blur'
    }
  ],
  code: [
    {
      required: true,
      message: '请输入验证码',
      trigger: 'blur'
    },
    {
      validator: validateCode,
      trigger: 'blur'
    }
  ],
}
// 后台验证码id
let codeId
// 后台的验证码
let code
// 获取后台验证码
const getCode = () => {
  auth.getCode(codeId).then(resp => {
    code = resp.data
  })
}
// 点击图片刷新验证码
const changeCode = () => {
  const codeImg = document.querySelector('#code')
  codeId = utils.getRandomId();
  codeImg.src = `${process.env.VUE_APP_CAPTCHA_URL}/util/getCodeImg?id=` + codeId
  codeImg.onload = () => getCode()
}
const toRegisterPage = () => {
  router.push('/register')
}
// 登录
const login = (formEl) => {
  utils.validFormAndInvoke(formEl, () => {
    auth.login(loginForm).then(resp => {
      if (resp.code === 200) {
        localStorage.setItem('authorization', resp.data)
        Vue.prototype.$notify({
          title: 'Tips',
          message: '登陆成功^_^',
          type: 'success',
          duration: 2000
        })
        router.push('/index')
      }
    }).catch(err => {
      console.log(err)
      //请求出错
      changeCode()
      getCode()
      Vue.prototype.$notify({
        title: 'Tips',
        message: err.response.data.errMsg,
        type: 'error',
        duration: 2000
      })
    })
  })
}

export default {
  loginForm,
  loginFormRules,
  code,
  getCode,
  changeCode,
  toRegisterPage,
  login
}
