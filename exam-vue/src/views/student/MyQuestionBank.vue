<template>
  <el-container>
       <!-- <el-header>
   <el-input v-model="queryInfo.bankName" @blur="contentChange" placeholder="题库名称"
                style="width: 220px;margin-top: 14px;"
                prefix-icon="el-icon-search"></el-input>
    </el-header> -->
    <el-main>
      <el-table
        ref="questionTable"
        highlight-current-row
        v-loading="loading"
        :border="true"
        :data="questionBankInfo"
        tooltip-effect="dark"
        style="width: 100%;" @selection-change="handleTableSectionChange">

        <el-table-column align="center"
                         prop="questionBank.bankName"
                         label="题库名称">
        </el-table-column>

        <el-table-column align="center"
                         prop="singleChoice"
                         label="单选题数量">
        </el-table-column>

        <el-table-column align="center"
                         prop="judge"
                         label="判断题数量">
        </el-table-column>


        <el-table-column align="center"
                         label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-wind-power" size="small"
      
                       @click="showTrainDialog(scope.row.questionBank.bankId)">开始训练
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页-->
      <el-pagination style="margin-top: 20px"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="queryInfo.pageNo"
                     :page-sizes="[10, 20, 30, 50]"
                     :page-size="queryInfo.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>

    </el-main>


    <el-dialog title="开始训练" width="55%" v-if="questionBankInfo.length!==0" :visible.sync="trainVisible" center>
      <h1>题库简介</h1>
      <el-card style="height: 60px;position: relative">
        <span style="position: absolute;font-size: 16px;font-weight: 400;top: 20px;">{{
            questionBankInfo[currentBankIndex].questionBank.bankName
          }}</span>
      </el-card>

      <h1>练习模式</h1>
      <el-card>

        <div class="btn-item el-col el-col-10" style="padding-left: 10px; padding-right: 10px;" @click="toTrainPage(1)">
          <div class="img-btn">
            <img src="../../assets/imgs/order.png">
            <div>
              顺序练习
            </div>
          </div>
        </div>

        <div class="btn-item el-col el-col-10" style="padding-left: 10px; padding-right: 10px;" @click="toTrainPage(2)">
          <div class="img-btn">
            <img src="../../assets/imgs/random.png">
            <div>
              随机训练
            </div>
          </div>
        </div>

      </el-card>

      <h1>题型训练</h1>
      <el-card>
        <div class="btn-item el-col el-col-7" style="margin-left: 18px;padding-left: 10px; padding-right: 10px;"
             @click="questionBankInfo[currentBankIndex].singleChoice> 0 ? toTrainPage(3) : $message.warning('当前类型题库暂未收录o(╥﹏╥)o')">
          <div class="img-btn">
            <img src="../../assets/imgs/single.png">
            <div>
              单选题({{ questionBankInfo[currentBankIndex].singleChoice }}题)
            </div>
          </div>
        </div>

        <!-- <div class="btn-item el-col el-col-7" style="padding-left: 10px; padding-right: 10px;"
             @click="questionBankInfo[currentBankIndex].multipleChoice > 0 ? toTrainPage(4) : $message.warning('当前类型题库暂未收录o(╥﹏╥)o')">
          <div class="img-btn">
            <img src="../../assets/imgs/multiple.png">
            <div>
              多选题({{ questionBankInfo[currentBankIndex].multipleChoice }}题)
            </div>
          </div>
        </div> -->

        <div class="btn-item el-col el-col-7" style="padding-left: 10px; padding-right: 10px;"
             @click="questionBankInfo[currentBankIndex].judge> 0 ? toTrainPage(5) : $message.warning('当前类型题库暂未收录o(╥﹏╥)o')">
          <div class="img-btn">
            <img src="../../assets/imgs/judge.png">
            <div>
              判断题({{ questionBankInfo[currentBankIndex].judge }}题)
            </div>
          </div>
        </div>

      </el-card>

    </el-dialog>

  </el-container>
</template>

<script>
import questionBank from '@/api/questionBank'
import wangke from "@/api/wangke";
export default {
  name: 'MyQuestionBank',
  data () {
    return {
      queryInfo: {
        username: localStorage.getItem("username"),
        bankName: '',
        pageNo: 1,
        pageSize: 10
      },
      //被选中的表格的信息
      selectedTable: [],
      //所有题库信息
      questionBankInfo: [
        {
          questionBank: {}
        }
      ],
      //当前被选中的操作
      operation: '',
      loading: true,
      //所有的题库条数
      total: 0,
      //添加题库的对话框
      addTableVisible: false,
      //添加题库的表单信息
      addForm: {
        bankName: ''
      },
      //添加表单的数据校验规则
      addFormRules: {
        bankName: [
          {
            required: true,
            message: '请输入题库名称',
            trigger: 'blur'
          },
        ]
      },
      //开始训练的模式选择的对话框
      trainVisible: false,
      //当前被点击训练的题库id
      currentBankId: 0,
      //当前被点击训练的题库在当前页面中数据的索引下标值
      currentBankIndex: 0
    }
  },
  created () {
    this.getBankInfo()
  },
  methods: {
    //获取所有的题库信息
    getBankInfo () {
      // 根据用户角色分配题库
      // wangke.getYonghuWangkeYeWuBankInfo({
      //     username: localStorage.getItem("username"),
      //   })
      //   .then((resp) => {
      //     console.log(resp.data)
      //   });

      questionBank.getBankHaveQuestionSumByType(this.queryInfo).then((resp) => {
        if (resp.code === 200) {
          this.questionBankInfo = resp.data.data
          this.total = resp.data.total
          this.loading = false
        } else {
          this.$notify({
            title: 'Tips',
            message: resp.message,
            type: 'error',
            duration: 2000
          })
        }
      })
    },
    //查询内容变化
    contentChange () {
      this.getBankInfo()
    },
    //表格部分行被选中
    handleTableSectionChange (row) {
      this.selectedTable = row
    },
    //分页插件的大小改变
    handleSizeChange (val) {
      this.queryInfo.pageSize = val
      this.getBankInfo()
    },
    //分页插件的页数
    handleCurrentChange (val) {
      this.queryInfo.pageNo = val
      this.getBankInfo()
    },
    //显示练习对话框
    showTrainDialog (bankId) {
      this.trainVisible = true
      this.currentBankId = bankId
      //找到数据对应的索引
      this.questionBankInfo.map((item, index) => {
        if (item.questionBank.bankId === bankId) this.currentBankIndex = index
      })
    },
    //跳转练习页面
    toTrainPage (trainType) {//trainType (1顺序,2随机,3单选,4多选,5判断)
      let bankInfo = this.questionBankInfo[this.currentBankIndex]
      if (bankInfo.multipleChoice > 0 || bankInfo.judge > 0 || bankInfo.singleChoice > 0) {//当前题库有题目  
        this.$router.push({path:'/train/' + this.currentBankId + '/' + trainType, query: { TikubankName: this.questionBankInfo[this.currentBankIndex].questionBank.bankName}})
        //  questionBankInfo[currentBankIndex].questionBank.bankName
      } else {
        this.$message.warning('当前题库暂无题目,o(╥﹏╥)o')
      }
    }
  }
}
</script>

<style scoped lang="scss">
@import "../../assets/css/student/myQuestionBank";
</style>

