<template>
  <el-container>
    <el-header>
      <!--操作的下拉框-->
      <el-select @change="operation" clearable v-model="queryInfo.examId"
                 placeholder="请选择考试" style="margin-bottom: 25px;">
        <el-option v-for="(item,index) in allExamInfo" :key="index" :label="item.examName"
                   :value="parseInt(item.examId)">
          <span style="float: left">{{ item.examName }}</span>
        </el-option>
      </el-select>

    </el-header>

    <el-main>
      <el-table
        ref="questionTable"
        highlight-current-row
        v-loading="loading"
        :border="true"
        :data="examRecords"
        tooltip-effect="dark"
        style="width: 100%;">

        <el-table-column align="center" prop="examName" label="考试名称"></el-table-column>

        <el-table-column align="center" prop="examTime" label="参考时间"></el-table-column>

        <el-table-column align="center" prop="trueName" label="参考人"></el-table-column>

        <el-table-column align="center" prop="logicScore" label="逻辑题得分"></el-table-column>

        <el-table-column align="center" label="是否批阅">
          <template slot-scope="scope">
            <span>{{ scope.row.totalScore === null ? '未批阅' : '已批阅' }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="总分">
          <template slot-scope="scope">
            <span>{{ scope.row.totalScore === null ? 0 : scope.row.totalScore }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button :type="scope.row.totalScore === null ? 'primary' : 'warning'" icon="el-icon-view" size="small"
                       :disabled="scope.row.totalScore !== null"
                       @click="$router.push('/markExam/' + scope.row.recordId)">
              {{ scope.row.totalScore === null ? '批阅' : '已批阅' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
      <el-pagination style="margin-top: 25px"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="queryInfo.pageNo"
                     :page-sizes="[10, 20, 30, 50]"
                     :page-size="queryInfo.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </el-main>
  </el-container>
</template>

<script>
import exam from '@/api/exam'
import user from '@/api/user'

export default {
  name: 'MarkManage',
  data () {
    return {
      queryInfo: {
        pageNo: 1,
        pageSize: 10
      },
      //考试记录
      examRecords: [],
      //表格数据加载
      loading: true,
      //所有考试信息
      allExamInfo: [],
      //总数
      total: 0
    }
  },
  created () {
    this.getExamRecords()
    this.getAllExamInfo()
  },
  methods: {
    async getExamRecords () {
      await exam.getExamRecord(this.queryInfo).then(async resp => {
        if (resp.code === 200) {
          await this.getAllExamInfo()
          let userIds = []
          resp.data.data.forEach(item => {
            if (userIds.every(id => id !== item.userId)) {
              userIds.push(item.userId)
            }
          })
          let userInfo = []
          await user.getUserByIds({ 'userIds': userIds.join(',') }).then(resp => {
            if (resp.code === 200) {
              userInfo = resp.data
            }
          })
          resp.data.data.forEach(item => {
            const currentUserInfo = userInfo.find(u => u.id === item.userId)
            item.trueName = currentUserInfo?.trueName
          })
          this.examRecords = resp.data.data
          // 当examRecords和examInfo准备好之后处理
          this.setExamName()
          this.total = resp.data.total
          this.loading = false
        }
      })
    },
    getAllExamInfo () {
      exam.allExamInfo().then((resp) => {
        if (resp.code === 200) {
          this.allExamInfo = resp.data
        }
      })
    },
    operation (v) {
      if (v === '') this.queryInfo.examId = null
      this.getExamRecords()
    },
    setExamName () {
      this.examRecords.forEach(item => {
        this.allExamInfo.forEach(i2 => {
          if (item.examId === i2.examId) {
            this.$set(item, 'examName', i2.examName)
          }
        })
      })
    },
    //分页页面大小改变
    handleSizeChange (val) {
      this.queryInfo.pageSize = val
      this.getExamRecords()
    },
    //分页插件的页数
    handleCurrentChange (val) {
      this.queryInfo.pageNo = val
      this.getExamRecords()
    },
  }
}
</script>

<style scoped lang="scss">
@import "../../assets/css/teacher/markManage";
</style>
