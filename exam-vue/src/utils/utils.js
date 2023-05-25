import auth from '@/api/auth'
import Vue from 'vue'
import router from '../router/index'

const getRandomId = () => {
  return Math.floor(Math.random() * 10000000);
}

// valid el form and invoke target method
const validFormAndInvoke = (formEl, success, message = '信息有误', fail = function () {
}) => {
  if (!formEl) {
    return
  }
  formEl.validate(valid => {
    if (valid) {// form valid succeed
      // do success function
      success()
      // reset fields
      formEl.resetFields()
    } else {// form valid fail
      Vue.prototype.$notify({
        title: 'Tips',
        message: message,
        type: 'error',
        duration: 2000
      })
      // do something when fail
      fail()
      return false
    }
  })
}
// check token and router link
const checkToken = (to) => {
  if (localStorage.getItem('authorization') !== null) {
    auth.checkToken()
      .then(resp => {
        if (resp.code === 200) {
          router.push(to)
        }
      })
      .catch(error => {
        localStorage.removeItem('authorization')
      })
  }
}

export default {
  validFormAndInvoke,
  checkToken,
  getRandomId
}
