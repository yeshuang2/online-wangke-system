<template>
  <div class="release_wrap">
    <el-main>
      <el-table
        ref="multipleTable"
        highlight-current-row
        v-loading="loading"
        :border="true"
        :data="getUserInfoData"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <el-table-column align="center" prop="id" label="id" width="65">
        </el-table-column>

        <el-table-column align="center" prop="username" label="用户名">
        </el-table-column>

        <el-table-column align="center" prop="trueName" label="姓名">
        </el-table-column>

        <el-table-column align="center" label="角色">
          <template slot-scope="scope">
            <span class="role" v-show="scope.row.roleId === 3">支行行长</span>
            <span class="role" v-show="scope.row.roleId === 2">部门经理</span>
            <span class="role" v-show="scope.row.roleId === 1">学生</span>
          </template>
        </el-table-column>
 <!--     基层员工、部门经理、支行行长三类职业 -->
              <el-table-column align="center" prop="threevalue" label="所属职业">
        </el-table-column>
<!--     //选择某某支行 -->
              <el-table-column align="center" prop="zhihangvalue" label="所属支行">
        </el-table-column>

        <el-table-column align="center" label="创建时间">
          <template slot-scope="scope">
            {{ scope.row.createDate }}
          </template>
        </el-table-column>

        <el-table-column align="center" label="状态">
          <template slot-scope="scope">
            {{ scope.row.status === 1 ? "正常" : "待审核" }}
          </template>
        </el-table-column>
      </el-table>
<br>
 
    </el-main>

  </div>
</template>

<script>
import user from "@/api/user";
export default {
      data() {
    return {
      //查询用户的参数
      queryInfo: {
        loginName: localStorage.getItem("username"),
        trueName: null,
        pageNo: 1,
        pageSize: 1,
      },
      getUserInfoData:[], // 用户个人信息数据
loading:true,
    };
  },
  created(){

 this.getuserbyname(); //查询个人信息

  },
  methods:{
    getuserbyname(){
         user.getUserInfo(this.queryInfo).then((resp) => {
        if (resp.code === 200) {
          this.getUserInfoData = resp.data.data;
          console.log(resp.data.data)
          this.total = resp.data.total;
          this.loading = false;
        } else {
          this.$notify({
            title: "Tips",
            message: "获取信息失败",
            type: "error",
            duration: 2000,
          });
        }
      });
    }

  }

}
</script>

<style>

</style>