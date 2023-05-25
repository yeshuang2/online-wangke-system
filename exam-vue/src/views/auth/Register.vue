<template>
  <el-container>
    <el-main>
      <el-card class="box-card" shadow="always">
        <div slot="header" class="card-header">
          <p>领航培训管理系统</p>
        </div>

        <div>
          <el-form
            :model="registerForm"
            :rules="registerFormRules"
            ref="registerForm"
            :status-icon="true"
            label-width="100px"
          >
            <el-form-item prop="username">
              <el-input
                prefix-icon="el-icon-user"
                v-model="registerForm.username"
                placeholder="账号"
              ></el-input>
            </el-form-item>

            <el-form-item prop="trueName">
              <el-input
                prefix-icon="el-icon-s-check"
                v-model="registerForm.trueName"
                placeholder="姓名"
              ></el-input>
            </el-form-item>

            <el-form-item prop="password">
              <el-input
                prefix-icon="el-icon-lock"
                v-model="registerForm.password"
                placeholder="密码"
                show-password
              ></el-input>
            </el-form-item>

            <el-form-item prop="code">
              <el-input
                class="code"
                prefix-icon="el-icon-chat-line-round"
                v-model="registerForm.code"
                placeholder="验证码"
              ></el-input>
              <img
                :src="`${captchaUrl}/util/getCodeImg`"
                @click="changeCode"
                id="code"
                style="float: right; margin-top: 4px; cursor: pointer"
                title="看不清,点击刷新"
                alt="验证码"
              />
            </el-form-item>

            <el-form-item>
              <!-- 下拉 基层员工、部门经理、支行行长三类职业 角色3个 -->
              <el-select v-model="registerForm.threevalue" placeholder="请选择职业">
                <el-option
                  v-for="item in threeoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <!-- 下拉 选择某某支行 -->
              <el-select v-model="registerForm.zhihangvalue" placeholder="请选择支行">
                <el-option
                  v-for="item in zhihangoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button
                type="warning"
                @click="register($refs['registerForm'])"
                icon="el-icon el-icon-circle-plus"
                >注册
              </el-button>
              <el-button @click="toLoginPage" icon="el-icon el-icon-s-promotion"
                >去登陆</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </el-main>

    <Footer></Footer>
  </el-container>
</template>

<script>
import Footer from "@/components/Footer";
import RegisterFunc from "@/function-namespace/auth/RegisterFunc";

export default {
  name: "Register",
  components: { Footer },
  data() {
    return {
      ...RegisterFunc,
      captchaUrl: process.env.VUE_APP_CAPTCHA_URL,
      threeoptions: [
        {
          value: "基层员工",
          label: "基层员工",
        },
          {
          value: "销售部门",
          label: "销售部门",
        },
          {
          value: "后勤部门",
          label: "后勤部门",
        },
          {
          value: "技术部门",
          label: "技术部门",
        },
         {
          value: "其他部门",
          label: "其他部门",
        },
        {
          value: "部门经理",
          label: "部门经理",
        },
        {
          value: "支行行长",
          label: "支行行长",
        },
      ],
      threevalue: "",

      // xx支行zhihang
      zhihangoptions: [
        {
          value: "中国农业支行",
          label: "中国农业支行",
        },
        {
          value: "中国农商支行",
          label: "中国农商支行",
        },
        {
          value: "中国邮政支行",
          label: "中国邮政支行",
        },
      ],
      zhihangvalue: "",
    };
  },
  mounted() {
    RegisterFunc.changeCode();
  },
};
</script>

<style scoped lang="scss">
@import "../../assets/css/auth/register";
</style>
