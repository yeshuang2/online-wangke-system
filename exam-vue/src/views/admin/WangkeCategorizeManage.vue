<template>
  <div>
    <el-header>
      <el-button
        type="primary"
        style="margin-top: 10px"
        icon="el-icon-plus"
        @click="addTableVisible = true"
        >添加</el-button
      >
      <br />
    </el-header>

    <el-table :data="allMovesBank" style="width: 50%">
      <el-table-column align="center" prop="wangkeid" label="业务ID" width="80">
      </el-table-column>

      <el-table-column label="业务名称" width="380">
        <template slot-scope="scope">
          <el-popover trigger="hover" name="" placement="top">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.wangkeName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!--添加业务信息-->
    <el-dialog
      title="添加/更新业务"
      :visible.sync="addTableVisible"
      width="30%"
      @close="$refs['ruleForm'].resetFields()"
      center
    >
      <el-form :model="ruleForm" :rules="addFormRules" ref="ruleForm">
        <el-form-item label="题库名称" label-width="120px" prop="wangkeName">
          <el-input v-model="ruleForm.wangkeName"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addMovesBank">添 加</el-button>
        <el-button @click="addTableVisible = false">取 消</el-button>
        <el-button type="warning" @click="upadateMovesBank">更 新</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import wangke from "@/api/wangke";
import utils from "@/utils/utils";
export default {
  data() {
    return {
      allMovesBank: [],
      ruleForm: {
        wangkeName: "",
        wangkeid: "", //业务id
      },
      addTableVisible: false,
      addFormRules: {
        wangkeName: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
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

    //编辑
    handleEdit(index, row) {
      this.addTableVisible = true;
      this.ruleForm.wangkeName = row.wangkeName;
      this.ruleForm.wangkeid = row.wangkeid;
      //   console.log(index, row);
    },
    //更新
    upadateMovesBank() {
      wangke.updateMovesBank(this.ruleForm).then((resp) => {
        if (resp.code === 200) {
          this.$notify({
            title: "Tips",
            message: "更新成功",
            type: "success",
            duration: 2000,
          });
          this.addTableVisible = false; //关闭控件
          this.getwangkebankinfo(); //重新刷新页面数据
        }
      });
    },
    // 业务删除
    handleDelete(index, row) {
      console.log(row.wangkeid);
      wangke.deleteovesBank({ wangkeid: row.wangkeid }).then((resp) => {
        if (resp.code === 200) {
          this.$notify({
            title: "Tips",
            message: "删除成功",
            type: "success",
            duration: 2000,
          });
          this.getwangkebankinfo(); //重新刷新页面数据
        } else {
          this.$notify({
            title: "Tips",
            message: "删除失败",
            type: "error",
            duration: 2000,
          });
        }
      });
      console.log(index, row);
    },

    //添加题库
    addMovesBank() {
      utils.validFormAndInvoke(
        this.$refs["ruleForm"],
        () => {
          wangke.addMovesBank(this.ruleForm).then((resp) => {
            if (resp.code === 200) {
              this.getwangkebankinfo();
              this.$notify({
                title: "Tips",
                message: resp.message,
                type: "success",
                duration: 2000,
              });
            } else {
              this.$notify({
                title: "Tips",
                message: resp.message,
                type: "error",
                duration: 2000,
              });
            }
            this.addTableVisible = false;
          });
        },
        "请检查您所填写的信息是否有误"
      );
    },
  },
};
</script>

<style  scoped lang='scss'>
</style>