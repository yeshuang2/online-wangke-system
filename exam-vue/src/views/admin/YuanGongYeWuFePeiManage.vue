<template>
  <div>
    <el-form :model="addQuForm" ref="addQuForm" :rules="addQuFormRules">
      <el-form-item label="选择角色业务" label-width="120px" prop="yewuName">
        <el-select v-model="addQuForm.yewuName" placeholder="请选择角色业务">
          <el-option :value="'基层员工'" label="基层员工"></el-option>
          <el-option :value="'销售部门'" label="销售部门"></el-option>
          <el-option :value="'技术部门'" label="技术部门"></el-option>
          <el-option :value="'后勤部门'" label="后勤部门"></el-option>
          <el-option :value="'其他部门'" label="其他部门"></el-option>
          <el-option :value="'部门经理'" label="部门经理"></el-option>
          <el-option :value="'支行行长'" label="支行行长"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="选网课业务" label-width="120px" prop="wangkeid">
        <el-select
          multiple
          v-model="addQuForm.wangkeid"
          placeholder="请选择网课业务"
        >
          <el-option
            v-for="item in allMovesBank"
            :key="item.wangkeid"
            :label="item.wangkeName"
            :value="item.wangkeid"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" style="margin-left: 120px">
      <el-button @click="addTableVisible = false">取 消</el-button>
      <el-button type="primary" @click="addYeWuinfo">确 定</el-button>
    </div>
  </div>
</template>

<script>
import wangke from "@/api/wangke";
import utils from "@/utils/utils";
export default {
  data() {
    return {
      //添加题目的表单信息
      addQuForm: {
        questionType: 1,
        questionLevel: 1,
        yewuName: "", //角色名称
        // yewuQuBinkid: "", //业务id
        // yewuQuBinkname: "", //业务
        wangkeid: "",
        wangkeName: "", //网课名称
        bankId: "",
        questionContent: "",
        images: [],
        analysis: "",
        createPerson: localStorage.getItem("username"),
      },
      //添加题目表单的验证规则
      addQuFormRules: {
        wangkeid: [
          {
            required: true,
            message: "请选择所属的业务",
            trigger: "blur",
          },
        ],
        yewuName: [
          {
            required: true,
            message: "请选择角色业务",
            trigger: "blur",
          },
        ],
      },
      //是否显示加入题库对话框
      addTableVisible: false,
      //题库信息
      allMovesBank: [],
      //是否显示业务添加题目的对话框
      addQuTableVisible: false,
    };
  },
  created() {
    this.getwangkebankinfo();
  },
  methods: {
    getwangkebankinfo() {
      wangke.getMovesBank().then((resp) => {
        if (resp.code === 200) {
          this.allMovesBank = resp.data;
        }
      });
    },
    //新增员工和业务的连接关系数据
    //提交加入题库的表单信息
    addYeWuinfo() {
      utils.validFormAndInvoke(
        this.$refs["addQuForm"],
        () => {
          let questionIds = this.addQuForm.wangkeid; // 获取网课的id
          wangke
            .addQuestTionYeWuInfo({
              yewuName: this.addQuForm.yewuName, //业务角色
              yewuQuBinkid: questionIds.join(","), // 对应的是网课的id
            })
            .then((resp) => {
              if (resp.code === 200) {
                this.getwangkebankinfo();
                this.$notify({
                  title: "Tips",
                  message: resp.message,
                  type: "success",
                  duration: 2000,
                });
              }
              this.addTableVisible = false;
            });
        },
        "请选择需要加入的业务"
      );
    },
  },
};
</script>

<style>
</style>