<template>
  <el-container>
    <el-main>
      <el-card class="box-card" shadow="always">
        <div slot="header" class="card-header">
          <p>领航培训管理系统</p>
        </div>

        <div>
          <el-form :model="loginForm" :rules="loginFormRules" ref="loginForm" :status-icon="true" label-width="100px">
            <el-form-item prop="username">
              <el-input prefix-icon="el-icon-user" v-model="loginForm.username" placeholder="账号" ></el-input>
            </el-form-item>

            <el-form-item prop="password">
              <el-input prefix-icon="el-icon-lock" v-model="loginForm.password" placeholder="密码" 
                        show-password></el-input>
            </el-form-item>

            <el-form-item prop="code">
              <el-input class="code" prefix-icon="el-icon-chat-line-round" v-model="loginForm.code"
                        placeholder="验证码"></el-input>
              <img @click="changeCode" id="code"
                   style="float: right;margin-top: 4px;cursor: pointer" title="看不清,点击刷新"
                   alt="验证码"/>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="login($refs['loginForm'])" icon="el-icon el-icon-s-promotion">登录
              </el-button>
              <el-button type="warning" @click="toRegisterPage" icon="el-icon el-icon-circle-plus">学员注册</el-button>
            </el-form-item>

          </el-form>
        </div>
      </el-card>
    </el-main>

    <Footer></Footer>
  </el-container>
</template>

<script>
import Footer from '@/components/Footer'
import LoginFunc from '@/function-namespace/auth/LoginFunc'
import loginFunc from '@/function-namespace/auth/LoginFunc'
import utils from '@/utils/utils'

export default {
  name: 'Login',
  components: {Footer},
  data() {
    return {
      ...LoginFunc,
      captchaUrl: process.env.VUE_APP_CAPTCHA_URL
    }
  },
  created() {
    // 检验用户是否存在token,存在直接跳转主页
    utils.checkToken('/index')
  },
  mounted() {
    loginFunc.changeCode()
  }
}
</script>

<style scoped lang="scss">
@import "../../assets/css/auth/login";

</style>
